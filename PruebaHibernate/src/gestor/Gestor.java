package gestor;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Clase que llama a los métodos protected de los gestores de Departamentos y
 * Empleados.
 */
public class Gestor {

	private GestorEmpleados ge = null;
	private GestorDepartamentos gd = null;

	public Gestor(GestorEmpleados ge, GestorDepartamentos gd) {
		this.ge = ge;
		this.gd = gd;
	}

	public void ejecutarConsultas() {
		System.out.println("***** PARTE 1: Consultas *****");
		// Datos de los emepleados por número de departamento
		ge.obtenerEmpleadosPorDeptNo((byte) 10);

		// Empleado con salario máximo
		ge.obtenerEmpleadoSalMax();

		// Mostrar información de los departamentos
		gd.mostrarDepPorNombres(new String[] { "CONTABILIDAD", "INVESTIGACION" });

		// Mostrar empleados de x departamento y oficio
		ge.obtenerEmpleadosPorDnombreYOficio("CONTABILIDAD", "DIRECTOR");

		// Mostrar empleados por fecha de alta
		ge.obtenerEmpleadosPorFechAlt(Date.valueOf("1990-12-17"));

		// Mostrar empleados por localidad del departamento, ordenados por salario
		ge.obtenerEmpleadosPorLocalidadOrdenadosPorSalario("MADRID", 0);

		// Mostrar director del empleado con la comisión más alta
		ge.obtenerDirectorEmpleMaxComision();

		// Mostrar empleado con salario máximo de x localidad del departamento
		ge.obtenerEmpleadosPorLocalidadOrdenadosPorSalario("BARCELONA", 1);
		System.out.println();
	}

	public void ejecutarOperaciones() {
		System.out.println("***** PARTE 2: Operaciones *****");

		// Insertar un nuevo departamento
		gd.insertarDepartamento("INFORMATICA", "BILBAO");

		// Insertar un nuevo empleado
		ge.insertarEmpleado("LASA", "DIRECTOR", (short) 0, Date.valueOf(LocalDate.now()), 2300, 1000, "INFORMATICA");

		// Modificar empleado
		ge.modificarSalarioYFechaAltPorApellido("GIL", 1300, Date.valueOf(LocalDate.now().minusDays(1)));

		ge.eliminarPorLocDepartamento("MADRID");

		// Eliminar empleado con máximo salario de x departamento
		ge.eliminarEmpleMaxSalPorDnombre("CONTABILIDAD");
	}
}
