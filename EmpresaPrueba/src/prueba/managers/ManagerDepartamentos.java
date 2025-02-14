package prueba.managers;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import prueba.entities.Departamento;

public class ManagerDepartamentos {

	private SessionFactory sessionFactory = null;

	public ManagerDepartamentos(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public ArrayList<Departamento> getDeparByNames(String[] names) throws HibernateException {
		ArrayList<Departamento> departamentos = null;
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			String hql = "From Departamento D Where D.dnombre In (:names)";
			Query<Departamento> q = session.createQuery(hql, Departamento.class);
			q.setParameterList("names", names);
			List<Departamento> filas = q.list();
			
			if (filas != null && filas.size() > 0) {
				departamentos = new ArrayList<Departamento>();
				departamentos.addAll(filas);
			}
		} catch(HibernateException e) {
			throw e;
		} finally {
			if (session != null)
				session.close();
		}
		
		return departamentos;
	}
}
