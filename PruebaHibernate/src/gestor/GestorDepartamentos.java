package gestor;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import prueba.Departamentos;
import utils.HibernateUtil;

public class GestorDepartamentos {

	public GestorDepartamentos() {
		
	}
	
	public void mostrarDepPorLocYNombre(String loc, String nombre) {
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();
        String hql = "from Departamentos as depar where depar.loc = '" + loc + "' and depar.dnombre = '" + nombre + "'";
        Query q = session.createQuery(hql);
        List<?> filas = q.list();

        for (int i = 0; i < filas.size(); i++) {
                Departamentos dep = (Departamentos) filas.get(i);

                System.out.println(dep.getDeptNo());
                System.out.println(dep.getDnombre());
                System.out.println(dep.getLoc());
        }
        
        session.close();
        //sesion.close();
	}
}
