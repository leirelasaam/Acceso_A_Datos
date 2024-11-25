package empresa;

import java.util.ArrayList;

import empresa.gestores.GestorEmpresa;
import empresa.modelo.Departamento;
import empresa.modelo.Empleado;

/**
 * Ejecuta la aplicación, donde se devuelven los resultados sobre la bbdd
 * empresa.
 */
public class Empresa {

	public static void main(String[] args) {
		GestorEmpresa ge = new GestorEmpresa();

		/**
		 * Los primeros 3 ejercicios ejecutan sentencias de consultas SQL. Es por ello,
		 * que el booleano esProcedimiento está a false.
		 */
		// EJERCICIO 1:
		System.out.println("EJERCICIO 1:");
		ArrayList<Departamento> dptos = ge.obtenerDepartamentos(false);
		ge.imprimirDepartamentos(dptos);

		// EJERCICIO 2:
		System.out.println("\nEJERCICIO 2:");
		int dept_no = 10;
		ArrayList<Empleado> empleados = ge.obtenerEmpleadosPorDepartamento(dept_no, false);
		ge.imprimirEmpleados(empleados, 2);

		// EJERCICIO 3:
		System.out.println("\nEJERCICIO 3:");
		System.out.println("*******************");
		Empleado empleado = ge.obtenerEmpleadoConSalMax(false);
		ge.imprimirEmpleado(empleado, 3);

		/**
		 * Para ejecutar los procedimientos, se utiliza el booleano esProcedimiento a
		 * true. De esta forma, se realiza el casteo correcto y se llama al
		 * procedimiento.
		 */

		// EJERCICIO 1 - PROCEDIMIENTO:
		System.out.println("\nEJERCICIO 1 - Procedimiento:");
		dptos = ge.obtenerDepartamentos(true);
		ge.imprimirDepartamentos(dptos);

		// EJERCICIO 2 - PROCEDIMIENTO:
		System.out.println("\nEJERCICIO 2 - Procedimiento:");
		empleados = ge.obtenerEmpleadosPorDepartamento(dept_no, true);
		ge.imprimirEmpleados(empleados, 2);

		// EJERCICIO 3 - PROCEDIMIENTO:
		System.out.println("\nEJERCICIO 3 - Procedimiento:");
		System.out.println("*******************");
		empleado = ge.obtenerEmpleadoConSalMax(true);
		ge.imprimirEmpleado(empleado, 3);
	}

}
