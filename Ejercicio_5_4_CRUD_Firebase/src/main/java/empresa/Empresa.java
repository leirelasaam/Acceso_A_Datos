package empresa;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.google.cloud.firestore.Firestore;
import empresa.bbdd.Conexion;
import empresa.bbdd.GestorDeConsultas;
import empresa.controlador.Controlador;

/**
 * Clase que ejecuta los ejercicios sobre la base de datos Empresa.
 */
public class Empresa {

	private static Firestore db = null;

	public static void main(String[] args) {
		try {
			db = Conexion.getConexion();
			System.out.println("> Conexión establecida a Firestore");

			GestorDeConsultas gdc = new GestorDeConsultas(db);
			Controlador c = new Controlador(gdc);

			c.insertarNuevoDepartamento("INFORMÁTICA", "BILBAO");
			c.insertarNuevoEmpleado("INFORMÁTICA", "LASA", "DIRECTOR", 2300, 1000);
			c.actualizarEmpleado("GIL", 1300, c.getTimestampAyer());
			c.eliminarEmpleadosPorDepartamento("MADRID");
			c.eliminarEmpleadoConSalarioMaximo("CONTABILIDAD");
			
			System.out.println("> Cerrando la conexión...");

			db.close();

		} catch (FileNotFoundException e) {
			System.out.println("> Error al cargar las credenciales: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("> Error al establecer la conexión con Firestore: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("> Error al cerrar la conexión con Firebase: " + e.getLocalizedMessage());
		}
	}

}
