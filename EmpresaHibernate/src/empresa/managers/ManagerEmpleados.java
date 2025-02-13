package empresa.managers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import empresa.entities.Empleado;

public class ManagerEmpleados {

	private static final Logger logger = Logger.getLogger(ManagerEmpleados.class);
	SessionFactory sesion = null;

	public ManagerEmpleados(SessionFactory sesion) {
		this.sesion = sesion;
	}

	public ArrayList<Empleado> obtenerEmpleados() {
		ArrayList<Empleado> empleados = null;
		Session session = null;

		try {
			session = sesion.openSession();
			String hql = "FROM Empleado";
			Query<Empleado> q = session.createQuery(hql, Empleado.class);
			List<Empleado> filas = q.list();

			if (filas.size() > 0) {
				empleados = new ArrayList<Empleado>();
				empleados.addAll(filas);
				logger.info("Se han cargado los empleados.");
			} else {
				logger.info("No hay empleados.");
			}

		} catch (HibernateException e) {
			logger.error("Error abriendo la sesión: " + e.getMessage());
		} catch (Exception e) {
			logger.error("Error en Hibernate: " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return empleados;
	}

	public ArrayList<Empleado> obtenerEmpleadosHQL(String query) throws Exception {
		ArrayList<Empleado> empleados = null;
		Session session = null;

		try {
			session = sesion.openSession();
			String hql = query;
			Query<Empleado> q = session.createQuery(hql, Empleado.class);
			List<Empleado> filas = q.list();

			if (filas.size() > 0) {
				empleados = new ArrayList<Empleado>();
				empleados.addAll(filas);
				logger.info("Se han cargado los empleados de la query: " + query + ".");
			} else {
				logger.info("No hay empleados con la query: " + query + ".");
			}

		} catch (Exception e) {
			logger.error("Error en Hibernate: " + e.getMessage());
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return empleados;
	}

	public Empleado obtenerEmpleadoPorId(short empNo) {
		Empleado em = null;
		Session session = null;

		try {
			session = sesion.openSession();
			em = session.get(Empleado.class, empNo);
			logger.info("Se ha cargado el empleado con emp_no " + empNo);
		} catch (HibernateException e) {
			logger.error("Error abriendo la sesión: " + e.getMessage());
		} catch (Exception e) {
			logger.error("Error en Hibernate: " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return em;
	}

	public void insertarEmpleado(Empleado em) throws Exception {
		Session session = null;
		Transaction tx = null;

		try {
			session = sesion.openSession();
			tx = session.beginTransaction();
			session.persist(em);
			tx.commit();
			logger.info("Se ha insertado el nuevo empleado " + em.getApellido());
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			logger.error("Error en Hibernate: " + e.getMessage());
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void actualizarEmpleado(Empleado em) throws Exception {
		Session session = null;
		Transaction tx = null;

		try {
			session = sesion.openSession();
			tx = session.beginTransaction();
			session.merge(em);
			tx.commit();
			logger.info("Se ha actualizado el empleado " + em.getApellido());
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			logger.error("Error en Hibernate: " + e.getMessage());
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	public void eliminarEmpleado(short empNo) throws Exception {
		Session session = null;
		Transaction tx = null;

		try {
			session = sesion.openSession();

			Empleado em = session.get(Empleado.class, empNo);

			if (em != null) {
				logger.info("Empleado a eliminar: " + em.toString());
				tx = session.beginTransaction();
				session.remove(em);
				tx.commit();
				logger.info("Se ha eliminado el empleado " + em.getApellido());
			} else {
				throw new Exception("No se ha eliminado el empleado.");
			}

		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			logger.error("Error en Hibernate: " + e.getMessage());
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
