package gestor;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import pojos.Departamentos;
import utils.DBQueries;
import utils.HibernateUtil;

/**
 * Clase que contiene los métodos para realizar consultas u operaciones
 * relacionadas con la tabla Departamentos.
 */
public class GestorDepartamentos {

	SessionFactory sesion = null;

	public GestorDepartamentos(SessionFactory sesion) {
		this.sesion = sesion;
	}

	protected void mostrarDepPorNombres(String[] dnombres) {
		Session session = sesion.openSession();
		String hql = DBQueries.DS_POR_NOM;
		Query<Departamentos> q = session.createQuery(hql, Departamentos.class);
		// Parameter list para varios valores, una colección
		q.setParameterList("dnombres", dnombres);
		List<Departamentos> filas = q.list();

		System.out.println("*** VISUALIZAR DATOS DE DEPARTAMENTOS ***");
		if (filas.size() > 0) {
			for (int i = 0; i < filas.size(); i++) {
				Departamentos dep = filas.get(i);
				System.out.println(dep.toStringFormat1());
			}
		} else {
			System.out.println("> No hay departamentos.");
		}

		session.close();
	}

	private byte obtenerNuevoDeptNo() {
		byte num = 0;

		Session session = sesion.openSession();
		String hql = DBQueries.MAX_D;
		Query<Departamentos> q = session.createQuery(hql, Departamentos.class);
		List<Departamentos> filas = q.list();

		if (filas.size() > 0) {
			Departamentos dep = filas.get(0);
			num = (byte) (dep.getDeptNo() + 10);
		}
		session.close();

		return num;
	}
	
	protected void insertarDepartamento(String dnombre, String loc) {
	    SessionFactory sesion = HibernateUtil.getSessionFactory();
	    Session session = null;
	    Transaction tx = null;

	    try {
	        session = sesion.openSession();        
	        tx = session.beginTransaction();

	        Departamentos d = new Departamentos();
	        byte deptNo = obtenerNuevoDeptNo();

	        // Si la consulta para obtener el nuevo deptNo no ha ido bien, lanzar excepción
	        if (deptNo == 0)
	            throw new Exception("No se ha podido obtener el número de departamento más alto.");

	        d.setDeptNo(deptNo);
	        d.setDnombre(dnombre);
	        d.setLoc(loc);
	        d.setEmpleadoses(null);

	        // SAVE está deprecated, se recomienda usar PERSIST
	        session.persist(d);

	        // Si falla el commit se va a la excepción
	        tx.commit();
	        System.out.println("*** DEPARTAMENTO INSERTADO ***");
	        System.out.println(d.toStringFormat1());
	    } catch (Exception e) {
	        System.out.println("> Error al insertar el departamento " + dnombre + " en la localidad " + loc + ".");
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close(); 
	        }
	    }
	}
	
	protected Departamentos obtenerDepartamentoPorNombre(String dnombre) {
		Departamentos d = null;

		Session session = sesion.openSession();
		String hql = DBQueries.D_POR_NOM;
		Query<Departamentos> q = session.createQuery(hql, Departamentos.class);
		q.setParameter("dnombre", dnombre);
		q.setMaxResults(1);
		
		// No obtener como lista, ya que solo debe devolver uno o null
		d = q.uniqueResult();

		session.close();

		return d;
	}

}