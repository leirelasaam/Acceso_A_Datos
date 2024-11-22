package empresa;

import java.util.ArrayList;

import empresa.gestores.GestorEmpresa;
import empresa.modelo.Departamento;
import empresa.modelo.Empleado;

public class Empresa {

	public static void main(String[] args) {
		GestorEmpresa ge = new GestorEmpresa();
		
		// EJERCICIO 1:
		System.out.println("EJERCICIO 1:");
		ArrayList<Departamento> dptos = ge.obtenerDepartamentos();
		ge.imprimirDepartamentos(dptos);
		
		// EJERCICIO 2:
		System.out.println("EJERCICIO 2:");
		int dept_no = 10;
		ArrayList<Empleado> empleados = ge.obtenerEmpleadosPorDepartamento(dept_no);
		ge.imprimirEmpleados(empleados, 2);
	}

}
