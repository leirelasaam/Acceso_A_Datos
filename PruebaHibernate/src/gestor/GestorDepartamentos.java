package gestor;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import pojos.Departamentos;

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
}