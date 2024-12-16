package gestor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import pojos.Departamentos;
import pojos.Empleados;
import utils.HibernateUtil;

public class GestorEmpleados {

	SessionFactory sesion = null;

	public GestorEmpleados(SessionFactory sesion) {
		this.sesion = sesion;
	}

	public void obtenerEmpleadosPorDeptNo(byte deptNo) {
		Session session = sesion.openSession();
		String hql = "FROM Empleados as E WHERE E.departamentos.deptNo = :deptNo";
		Query q = session.createQuery(hql);
		q.setParameter("deptNo", deptNo);
		List<?> filas = q.list();

		System.out.println("*** EMPLEADOS DEL DEPARTAMENTO " + deptNo + " ***");

		if (filas.size() > 0) {
			for (int i = 0; i < filas.size(); i++) {
				Empleados e = (Empleados) filas.get(i);
				System.out.println(e.toStringFormat1());
			}
		} else {
			System.out.println("> No hay empleados.");
		}

		session.close();
	}

	public void obtenerEmpleadoSalMax() {
		Session session = sesion.openSession();
		String hql = "FROM Empleados as E ORDER BY E.salario DESC";
		Query q = session.createQuery(hql);
		q.setMaxResults(1);
		List<?> filas = q.list();

		System.out.println("*** EMPLEADO CON MAYOR SALARIO ***");

		if (filas.size() > 0) {
			Empleados e = (Empleados) filas.get(0);
			System.out.println(e.toStringFormat2());
			System.out.println(e.getDepartamentos().toStringFormat1());
		} else {
			System.out.println("> No hay empleados.");
		}

		session.close();
	}

	public void obtenerEmpleadosPorDnombreYOficio(String dnombre, String oficio) {
		Session session = sesion.openSession();
		String hql = "FROM Empleados as E WHERE E.departamentos.dnombre = :dnombre AND E.oficio  = :oficio";
		Query q = session.createQuery(hql);
		q.setParameter("dnombre", dnombre).setParameter("oficio", oficio);
		List<?> filas = q.list();

		System.out.println("*** EMPLEADOS DEL DEPARTAMENTO " + dnombre + " Y OFICIO " + oficio + " ***");

		if (filas.size() > 0) {
			for (int i = 0; i < filas.size(); i++) {
				Empleados e = (Empleados) filas.get(i);
				System.out.println(e.toStringFormat2());
			}
		} else {
			System.out.println("> No hay empleados.");
		}

		session.close();
	}

	public void obtenerEmpleadosPorFechAlt(Date fecha) {
		Session session = sesion.openSession();
		String hql = "FROM Empleados as E WHERE E.fechaAlt = :fechaAlt";
		Query q = session.createQuery(hql);
		q.setParameter("fechaAlt", fecha);
		List<?> filas = q.list();

		System.out.println("*** EMPLEADOS CON FECHA DE ALTA " + fecha + " ***");

		if (filas.size() > 0) {
			for (int i = 0; i < filas.size(); i++) {
				Empleados e = (Empleados) filas.get(i);
				System.out.println(e.toStringFormat2());
			}
		} else {
			System.out.println("> No hay empleados.");
		}

		session.close();
	}

	public void obtenerEmpleadosPorLocalidadOrdenadosPorSalario(String loc, int limite) {
		Session session = sesion.openSession();

		String limit = null;
		if (limite == 0)
			limit = "";
		else
			limit = " LIMIT " + limite;

		String hql = "FROM Empleados as E WHERE E.departamentos.loc = :loc ORDER BY E.salario DESC" + limit;
		Query q = session.createQuery(hql);
		q.setParameter("loc", loc);
		List<?> filas = q.list();

		System.out.println("*** EMPLEADOS DEL DEPARTAMENTO DE " + loc + " ***");

		if (filas.size() > 0) {
			for (int i = 0; i < filas.size(); i++) {
				Empleados e = (Empleados) filas.get(i);
				System.out.println(e.toStringFormat1());
			}
		} else {
			System.out.println("> No hay empleados.");
		}

		session.close();
	}

	public void obtenerDirectorEmpleMaxComision() {
		Session session = sesion.openSession();
		String hql = "FROM Empleados as E WHERE E.empNo = (SELECT EM.dir FROM Empleados as EM WHERE EM.comision IS NOT NULL ORDER BY EM.comision DESC LIMIT 1)";
		Query q = session.createQuery(hql);
		List<?> filas = q.list();
		q.setMaxResults(1);

		System.out.println("*** DIRECTOR DEL EMPLEADO CON MAYOR COMISIÓN ***");

		if (filas.size() > 0) {
			Empleados e = (Empleados) filas.get(0);
			System.out.println(e.toStringFormat1());
		} else {
			System.out.println("> No hay empleados.");
		}

		session.close();
	}

	private short obtenerNuevoEmpNo() {
		short num = 0;

		Session session = sesion.openSession();
		String hql = "FROM Empleados as E WHERE E.empNo = (SELECT MAX(EM.empNo) FROM Empleados as EM)";
		Query q = session.createQuery(hql);
		List<?> filas = q.list();

		if (filas.size() > 0) {
			Empleados dep = (Empleados) filas.get(0);
			num = (short) (dep.getEmpNo() + 1);
		}
		session.close();

		return num;
	}

	@SuppressWarnings("deprecation")
	public void insertarEmpleado(String apellido, String oficio, short dir, Date fechaAlt, float salario,
			float comision, String dnombre) {
		Session session = null;
		Transaction tx = null;

		try {
			session = sesion.openSession();
			tx = session.beginTransaction();

			Empleados e = new Empleados();
			short empNo = obtenerNuevoEmpNo();

			// Si la consulta para obtener el nuevo empNo no ha ido bien, lanzar excepción
			if (empNo == 0)
				throw new Exception("No se ha podido obtener el número de empleado más alto.");

			e.setEmpNo(empNo);
			e.setApellido(apellido);
			e.setOficio(oficio);
			e.setFechaAlt(fechaAlt);
			e.setComision(comision);
			e.setSalario(salario);
			if (dir != 0)
				e.setDir(dir);

			// Obtener el departamento por nombre
			GestorDepartamentos gd = new GestorDepartamentos(sesion);
			Departamentos d = gd.obtenerDepartamentoPorNombre(dnombre);
			
			if (d == null)
				throw new Exception("No se ha encontrado el departamento " + dnombre);
			
			e.setDepartamentos(d);

			session.save(e);

			// Si falla el commit se va a la excepción
			tx.commit();
			System.out.println("*** EMPLEADO INSERTADO ***");
			System.out.println(e.toStringFormat4());
		} catch (Exception e) {
			System.out.println("> Error al insertar el empleado " + apellido + " en el departamento " + dnombre + ".");
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	private Empleados obtenerEmpleadoPorApellido(String apellido) {
		Empleados e = null;

		Session session = sesion.openSession();
		String hql = "FROM Empleados as E WHERE E.apellido = :apellido";
		Query q = session.createQuery(hql);
		q.setParameter("apellido", apellido);
		List<?> filas = q.list();
		q.setMaxResults(1);

		if (filas.size() > 0) {
			e = (Empleados) filas.get(0);
		}
		session.close();

		return e;
	}
	
	@SuppressWarnings("deprecation")
	public void modificarSalarioYFechaAltPorApellido(String apellido, float salario, Date fechaAlt) {
		Session session = null;
		Transaction tx = null;

		try {
			session = sesion.openSession();
			tx = session.beginTransaction();
			
			Empleados e = obtenerEmpleadoPorApellido(apellido);

			// Si la consulta para obtener el empleado no ha ido bien, lanzar excepción
			if (e == null)
				throw new Exception("No se ha encontrado un empleado con el apellido " + apellido + ".");
			
			e.setSalario(salario);
			e.setFechaAlt(fechaAlt);
			
			session.update(e);
			
			tx.commit();
			System.out.println("*** EMPLEADO MODIFICADO ***");
			System.out.println(e.toStringFormat4());
		} catch (Exception e) {
			System.out.println("> Error al modificar el empleado " + apellido + ".");
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private Set<Empleados> empleadosPorLocDep(String loc){
		Set<Empleados> e = null;

		Session session = sesion.openSession();
		String hql = "FROM Departamentos as D WHERE D.loc = :loc";
		Query q = session.createQuery(hql);
		q.setParameter("loc", loc);
		List<?> filas = q.list();
		q.setMaxResults(1);

		if (filas.size() > 0) {
			Departamentos d = (Departamentos) filas.get(0);
			System.out.println(d.toStringFormat1());
			e = (Set<Empleados>) d.getEmpleadoses();
		}
		session.close();

		return e;
	}
	
	public void eliminarPorLocDepartamento(String loc) {
		Session session = null;
		Transaction tx = null;

		try {
			session = sesion.openSession();
			//tx = session.beginTransaction();
			
			Set<Empleados> empleados = empleadosPorLocDep(loc);
			
			if (empleados != null && !empleados.isEmpty()) {
				for (Empleados e : empleados) {
					System.out.println(e.toStringFormat4());
				}
			} else {
				System.out.println("Empleados es nulo");
			}

			
		} catch (Exception e) {
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}