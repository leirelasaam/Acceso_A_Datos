package prueba.managers;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import prueba.entities.Empleado;

public class ManagerEmpleados {

	private SessionFactory sessionFactory = null;

	public ManagerEmpleados(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public ArrayList<Empleado> getAll() throws HibernateException {
		ArrayList<Empleado> empleados = null;
		Session session = null;

		try {
			// Este puede lanzar HibernateException
			session = sessionFactory.openSession();
			String hql = "From Empleado E Join Fetch E.departamento";
			Query<Empleado> q = session.createQuery(hql, Empleado.class);
			List<Empleado> filas = q.list();

			if (filas != null && filas.size() > 0) {
				empleados = new ArrayList<Empleado>();
				empleados.addAll(filas);
			}
		} catch (HibernateException e) {
			throw e;
		} finally {
			if (session != null)
				session.close();
		}

		return empleados;
	}

	public ArrayList<Empleado> getEmpleadosPorDep(int deptNo) throws HibernateException {
		ArrayList<Empleado> empleados = null;
		Session session = null;

		try {
			// Este puede lanzar HibernateException
			session = sessionFactory.openSession();
			String hql = "From Empleado E Join Fetch E.departamento Where E.departamento.deptNo = :deptNo";
			Query<Empleado> q = session.createQuery(hql, Empleado.class);
			q.setParameter("deptNo", deptNo);
			List<Empleado> filas = q.list();

			if (filas != null && filas.size() > 0) {
				empleados = new ArrayList<Empleado>();
				empleados.addAll(filas);
			}
		} catch (HibernateException e) {
			throw e;
		} finally {
			if (session != null)
				session.close();
		}

		return empleados;
	}
	
	public Empleado getEmpleMaxSal() throws HibernateException {
		Empleado empleado = null;
		Session session = null;

		try {
			// Este puede lanzar HibernateException
			session = sessionFactory.openSession();
			String hql = "From Empleado E Join Fetch E.departamento Order By E.salario DESC Limit 1";
			Query<Empleado> q = session.createQuery(hql, Empleado.class);
			q.setMaxResults(1);
			empleado = q.uniqueResult();
			
		} catch (HibernateException e) {
			throw e;
		} finally {
			if (session != null)
				session.close();
		}

		return empleado;
	}
}
