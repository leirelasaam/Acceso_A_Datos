package empresa.managers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import empresa.entities.Departamento;

public class ManagerDepartamentos {

	private static final Logger logger = Logger.getLogger(ManagerDepartamentos.class);
	SessionFactory sesion = null;

	public ManagerDepartamentos(SessionFactory sesion) {
		this.sesion = sesion;
	}

	public ArrayList<Departamento> obtenerDepartamentos() {
		ArrayList<Departamento> departamentos = null;
		Session session = null;

		try {
			session = sesion.openSession();
			String hql = "FROM Departamento";
			Query<Departamento> q = session.createQuery(hql, Departamento.class);
			List<Departamento> filas = q.list();

			if (filas.size() > 0) {
				departamentos = new ArrayList<Departamento>();
				departamentos.addAll(filas);
				logger.info("Se han cargado los departamentos.");
			} else {
				logger.info("No hay departamentos.");
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

		return departamentos;
	}

	public Departamento obtenerDepartamentoPorId(byte deptNo) {
		Departamento d = null;
		Session session = null;

		try {
			session = sesion.openSession();
			d = session.get(Departamento.class, deptNo);
			logger.info("Se ha cargado el departamento con dept_no " + deptNo);
		} catch (HibernateException e) {
			logger.error("Error abriendo la sesión: " + e.getMessage());
		} catch (Exception e) {
			logger.error("Error en Hibernate: " + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return d;
	}

	public ArrayList<Departamento> obtenerDepartamentosHQL(String query) throws Exception {
		ArrayList<Departamento> departamentos = null;
		Session session = null;

		try {
			session = sesion.openSession();
			String hql = query;
			Query<Departamento> q = session.createQuery(hql, Departamento.class);
			List<Departamento> filas = q.list();

			if (filas.size() > 0) {
				departamentos = new ArrayList<Departamento>();
				departamentos.addAll(filas);
				logger.info("Se han cargado los departamentos de la query: " + query + ".");
			} else {
				logger.info("No hay departamentos con la query: " + query + ".");
			}

		} catch (Exception e) {
			logger.error("Error en Hibernate: " + e.getMessage());
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return departamentos;
	}

	public void insertarDepartamento(Departamento d) throws Exception {
		Session session = null;
		Transaction tx = null;

		try {
			session = sesion.openSession();
			tx = session.beginTransaction();
			session.persist(d);
			tx.commit();
			logger.info("Se ha insertado el nuevo departamento " + d.getDnombre());
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

	public void actualizarDepartamento(Departamento d) throws Exception {
		Session session = null;
		Transaction tx = null;

		try {
			session = sesion.openSession();
			tx = session.beginTransaction();
			session.merge(d);
			tx.commit();
			logger.info("Se ha actualizado el departamento " + d.getDnombre());
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

	public void eliminarDepartamento(byte deptNo) throws Exception {
		Session session = null;
		Transaction tx = null;

		try {
			session = sesion.openSession();

			Departamento d = session.get(Departamento.class, deptNo);

			if (d != null) {
				logger.info("Departamento a eliminar: " + d.toString());
				tx = session.beginTransaction();
				session.remove(d);
				tx.commit();
				logger.info("Se ha eliminado el departamento " + d.getDnombre());
			} else {
				throw new Exception("No se ha eliminado el departamento.");
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
