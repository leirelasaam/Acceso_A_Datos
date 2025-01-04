package ejecutor;

import org.hibernate.SessionFactory;

import gestor.Gestor;
import gestor.GestorDepartamentos;
import gestor.GestorEmpleados;
import utils.HibernateUtil;

/**
 * Clase que ejecuta la aplicación.
 * @author Leire Lasa
 */
public class Ejecutor {

	public static void main(String[] args) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		GestorEmpleados ge = new GestorEmpleados(sesion);
		GestorDepartamentos gd = new GestorDepartamentos(sesion);
		
		Gestor g = new Gestor(ge, gd);
		g.ejecutarConsultas();
		g.ejecutarOperaciones();
		
		// Cerrar la sesión
		sesion.close();
	}

}
