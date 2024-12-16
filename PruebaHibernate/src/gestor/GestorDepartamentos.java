package gestor;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import pojos.Departamentos;
import utils.HibernateUtil;

public class GestorDepartamentos {

	SessionFactory sesion = null;

	public GestorDepartamentos(SessionFactory sesion) {
		this.sesion = sesion;
	}

	public void mostrarDepPorNombres(String[] dnombres) {
		Session session = sesion.openSession();
		String hql = "FROM Departamentos as D WHERE D.dnombre IN(:dnombres)";
		Query q = session.createQuery(hql);
		// Parameter list para varios valores, una colección
		q.setParameterList("dnombres", dnombres);
		List<?> filas = q.list();

		System.out.println("*** VISUALIZAR DATOS DE DEPARTAMENTOS ***");
		if (filas.size() > 0) {
			for (int i = 0; i < filas.size(); i++) {
				Departamentos dep = (Departamentos) filas.get(i);
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
		String hql = "FROM Departamentos as D WHERE D.deptNo = (SELECT MAX(DE.deptNo) FROM Departamentos as DE)";
		Query q = session.createQuery(hql);
		List<?> filas = q.list();

		if (filas.size() > 0) {
			Departamentos dep = (Departamentos) filas.get(0);
			num = (byte) (dep.getDeptNo() + 10);
		}
		session.close();

		return num;
	}
	
	@SuppressWarnings("deprecation")
	public void insertarDepartamento(String dnombre, String loc) {
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

	        session.save(d);

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
	
	public Departamentos obtenerDepartamentoPorNombre(String dnombre) {
		Departamentos d = null;

		Session session = sesion.openSession();
		String hql = "FROM Departamentos as D WHERE D.dnombre = :dnombre";
		Query q = session.createQuery(hql);
		q.setParameter("dnombre", dnombre);
		List<?> filas = q.list();
		q.setMaxResults(1);

		if (filas.size() > 0) {
			d = (Departamentos) filas.get(0);
		}
		session.close();

		return d;
	}

}