package gestor;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import pojos.Departamentos;
import pojos.Empleados;
import utils.DBQueries;

/**
 * Clase que contiene los métodos para realizar consultas u operaciones
 * relacionadas con la tabla Empleados.
 */
public class GestorEmpleados {

	SessionFactory sesion = null;

	public GestorEmpleados(SessionFactory sesion) {
		this.sesion = sesion;
	}

	protected void obtenerEmpleadosPorDeptNo(byte deptNo) {
		Session session = sesion.openSession();
		String hql = DBQueries.E_POR_DEPTNO;
		Query<Empleados> q = session.createQuery(hql, Empleados.class);
		q.setParameter("deptNo", deptNo);
		List<Empleados> filas = q.list();

		System.out.println("*** EMPLEADOS DEL DEPARTAMENTO " + deptNo + " ***");

		if (filas.size() > 0) {
			for (int i = 0; i < filas.size(); i++) {
				Empleados e = filas.get(i);
				System.out.println(e.toStringFormat1());
			}
		} else {
			System.out.println("> No hay empleados.");
		}

		session.close();
	}

	protected void obtenerEmpleadoSalMax() {
		Session session = sesion.openSession();
		String hql = DBQueries.SAL_MAX;
		Query<Empleados> q = session.createQuery(hql, Empleados.class);
		q.setMaxResults(1);

		// No obtener como lista, ya que solo puede devolver uno o null
		Empleados e = q.uniqueResult();

		System.out.println("*** EMPLEADO CON MAYOR SALARIO ***");

		if (e != null) {
			System.out.println(e.toStringFormat2());
			System.out.println(e.getDepartamentos().toStringFormat1());
		} else {
			System.out.println("> No hay empleados.");
		}

		session.close();
	}

	protected void obtenerEmpleadosPorDnombreYOficio(String dnombre, String oficio) {
		Session session = sesion.openSession();
		String hql = DBQueries.E_POR_DNOM_Y_OF;
		Query<Empleados> q = session.createQuery(hql, Empleados.class);
		q.setParameter("dnombre", dnombre).setParameter("oficio", oficio);
		List<Empleados> filas = q.list();

		System.out.println("*** EMPLEADOS DEL DEPARTAMENTO " + dnombre + " Y OFICIO " + oficio + " ***");

		if (filas.size() > 0) {
			for (int i = 0; i < filas.size(); i++) {
				Empleados e = filas.get(i);
				System.out.println(e.toStringFormat2());
			}
		} else {
			System.out.println("> No hay empleados.");
		}

		session.close();
	}

	protected void obtenerEmpleadosPorFechAlt(Date fecha) {
		Session session = sesion.openSession();
		String hql = DBQueries.E_POR_FECHA;
		Query<Empleados> q = session.createQuery(hql, Empleados.class);
		q.setParameter("fechaAlt", fecha);
		List<Empleados> filas = q.list();

		System.out.println("*** EMPLEADOS CON FECHA DE ALTA " + fecha + " ***");

		if (filas.size() > 0) {
			for (int i = 0; i < filas.size(); i++) {
				Empleados e = filas.get(i);
				System.out.println(e.toStringFormat2());
			}
		} else {
			System.out.println("> No hay empleados.");
		}

		session.close();
	}

	protected void obtenerEmpleadosPorLocalidadOrdenadosPorSalario(String loc, int limite) {
		Session session = sesion.openSession();

		String limit = null;
		if (limite == 0)
			limit = "";
		else
			limit = " LIMIT " + limite;

		String hql = DBQueries.E_POR_LOC_OR + limit;
		Query<Empleados> q = session.createQuery(hql, Empleados.class);
		q.setParameter("loc", loc);
		List<Empleados> filas = q.list();

		System.out.println("*** EMPLEADOS DEL DEPARTAMENTO DE " + loc + " ***");

		if (filas.size() > 0) {
			for (int i = 0; i < filas.size(); i++) {
				Empleados e = filas.get(i);
				System.out.println(e.toStringFormat1());
			}
		} else {
			System.out.println("> No hay empleados.");
		}

		session.close();
	}

	protected void obtenerDirectorEmpleMaxComision() {
		Session session = sesion.openSession();
		String hql = DBQueries.DIR_EMPLE_COM_MAX;
		Query<Empleados> q = session.createQuery(hql, Empleados.class);
		q.setMaxResults(1);

		// No obtener como lista, ya que solo puede devolver uno o null
		Empleados e = q.uniqueResult();

		System.out.println("*** DIRECTOR DEL EMPLEADO CON MAYOR COMISIÓN ***");

		if (e != null) {
			System.out.println(e.toStringFormat3());
		} else {
			System.out.println("> No hay empleados.");
		}

		session.close();
	}

	private short obtenerNuevoEmpNo() {
		short num = 0;

		Session session = sesion.openSession();
		String hql = DBQueries.MAX_E;
		Query<Empleados> q = session.createQuery(hql, Empleados.class);
		List<Empleados> filas = q.list();

		if (filas.size() > 0) {
			Empleados dep = filas.get(0);
			num = (short) (dep.getEmpNo() + 1);
		}
		session.close();

		return num;
	}

	protected void insertarEmpleado(String apellido, String oficio, short dir, Date fechaAlt, float salario,
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

			// SAVE está deprecated, se recomienda usar PERSIST
			session.persist(e);

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

	protected Empleados obtenerEmpleadoPorApellido(String apellido) {
		Empleados e = null;

		Session session = sesion.openSession();
		String hql = DBQueries.E_POR_APE;
		Query<Empleados> q = session.createQuery(hql, Empleados.class);
		q.setParameter("apellido", apellido);
		q.setMaxResults(1);

		// No obtener como lista, ya que solo puede devolver uno o null
		e = q.uniqueResult();

		session.close();

		return e;
	}

	protected void modificarSalarioYFechaAltPorApellido(String apellido, float salario, Date fechaAlt) {
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

			// UPDATE está deprecated, se recomienda usar MERGE
			session.merge(e);

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

	private Set<Empleados> empleadosPorLocDep(String loc) {
		Set<Empleados> e = null;

		Session session = sesion.openSession();
		String hql = DBQueries.E_POR_LOC;
		Query<Empleados> q = session.createQuery(hql, Empleados.class);
		q.setParameter("loc", loc);
		List<Empleados> filas = q.list();

		if (filas.size() > 0) {
			e = new HashSet<Empleados>(filas);
		}

		session.close();

		return e;
	}

	protected void eliminarPorLocDepartamento(String loc) {
		Session session = null;
		Transaction tx = null;

		try {
			session = sesion.openSession();
			tx = session.beginTransaction();

			Set<Empleados> empleados = empleadosPorLocDep(loc);

			System.out.println("*** EMPLEADOS ELIMINADOS DEL DEPARTAMENTO DE " + loc + " ***");

			if (empleados != null && !empleados.isEmpty()) {
				for (Empleados e : empleados) {
					System.out.println(e.toStringFormat3());
					// DELETE está deprecated, se recomienda usar REMOVE
					session.remove(e);
				}
			} else {
				System.out.println("> No hay empleados en " + loc + ".");
			}

			tx.commit();

		} catch (Exception e) {
			System.out.println("> Error al eliminar los empleados de " + loc + ".");
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	protected Empleados obtenerEmpleadoSalMaxPorDnombre(String dnombre) {
		Empleados e = null;

		Session session = sesion.openSession();
		String hql = DBQueries.SAL_MAX_POR_DNOM;
		Query<Empleados> q = session.createQuery(hql, Empleados.class);
		q.setParameter("dnombre", dnombre);
		q.setMaxResults(1);

		// No obtener como lista, ya que solo puede devolver uno o null
		e = q.uniqueResult();

		session.close();

		return e;
	}

	protected void eliminarEmpleMaxSalPorDnombre(String dnombre) {
		Session session = null;
		Transaction tx = null;

		try {
			session = sesion.openSession();
			tx = session.beginTransaction();

			Empleados e = obtenerEmpleadoSalMaxPorDnombre(dnombre);

			System.out.println("*** EMPLEADO ELIMINADO DEL DEPARTAMENTO DE " + dnombre + " ***");

			if (e != null) {
				System.out.println(e.toStringFormat1());
				// DELETE está deprecated, se recomienda usar REMOVE
				session.remove(e);
			} else {
				System.out.println("> No hay empleados en " + dnombre + ".");
			}

			tx.commit();

		} catch (Exception e) {
			System.out.println("> Error al eliminar el empleado de " + dnombre + ".");
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}