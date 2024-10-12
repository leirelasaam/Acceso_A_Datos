package empresa.controlador;

import java.util.Calendar;

import com.google.cloud.Timestamp;

import java.util.Date;
import empresa.bbdd.GestorDeConsultas;

/**
 * Clase que contiene las llamadas a las diferentes operaciones CRUD definidas
 * en GestorDeConsultas.
 */
public class Controlador {

	private GestorDeConsultas gdc;

	public Controlador(GestorDeConsultas gdc) {
		this.gdc = gdc;
	}

	public void insertarNuevoDepartamento(String nomDep, String locDep) {
		try {
			gdc.insertDepartamento(nomDep, locDep);
			System.out.println("> Departamento nuevo añadido: " + nomDep + " " + locDep);
		} catch (Exception e) {
			System.out.println("> Error al insertar el nuevo departamento: " + e.getMessage());
		}
	}

	public void insertarNuevoEmpleado(String nomDep, String ape, String ofi, int sal, int com) {
		try {
			gdc.insertEmpleado(nomDep, ape, ofi, sal, com);
			System.out.println("> Empleado nuevo añadido: " + ape + " " + ofi);
		} catch (Exception e) {
			System.out.println("> Error al insertar el nuevo empleado: " + e.getMessage());
		}
	}

	public void actualizarEmpleado(String ape, int sal, Timestamp fecha) {
		try {
			gdc.updateEmpleadoPorApellido(ape, sal, fecha);
			System.out.println("> Empleado " + ape + " actualizado: " + sal + " " + fecha);
		} catch (Exception e) {
			System.out.println("> Error al actualizar el empleado: " + e.getMessage());
		}
	}

	public void eliminarEmpleadosPorDepartamento(String loc) {
		try {
			gdc.deleteEmpleadosPorDepartamento(loc);
			System.out.println("> Empleados del departamento localizado en " + loc + " eliminados");
		} catch (Exception e) {
			System.out.println("> Error al eliminar empleados: " + e.getMessage());
		}
	}

	public void eliminarEmpleadoConSalarioMaximo(String nom) {
		try {
			gdc.deleteEmpleadoMaxSalarioDelDpto(nom);
			System.out.println("> Empleado con salario máximo del departamento " + nom + " eliminado");
		} catch (Exception e) {
			System.out.println("> Error al eliminar empleado: " + e.getMessage());
		}
	}

	/**
	 * Obtiene la fecha de ayer en formato Timestamp de Firestore.
	 * 
	 * @return Fecha de ayer.
	 */
	public Timestamp getTimestampAyer() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		Date ayer = calendar.getTime();

		return Timestamp.of(ayer);
	}

}
