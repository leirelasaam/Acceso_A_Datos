package empresa.gestores;

import java.util.ArrayList;

import empresa.modelo.Departamento;
import empresa.modelo.Empleado;

public class GestorEmpresa {

	private GestorEmpleados gEmple = null;
	private GestorDepartamentos gDep = null;

	public GestorEmpresa() {

	}

	public ArrayList<Departamento> obtenerDepartamentos() {
		ArrayList<Departamento> dptos = null;

		if (null == gDep)
			gDep = new GestorDepartamentos();

		dptos = gDep.buscarTodosLosDepartamentos();

		return dptos;
	}

	public ArrayList<Empleado> obtenerEmpleadosPorDepartamento(int dept_no) {
		ArrayList<Empleado> empleados = null;

		if (null == gEmple)
			gEmple = new GestorEmpleados();

		empleados = gEmple.buscarEmpleadosPorDepartamento(dept_no);

		return empleados;
	}
	
	public Empleado obtenerEmpleadoConSalMax() {
		Empleado empleado = null;

		if (null == gEmple)
			gEmple = new GestorEmpleados();

		empleado = gEmple.buscarConMaxSalario();

		return empleado;
	}

	public void imprimirDepartamento(Departamento d) {
		if (d != null) {
			System.out.println(d.toString());
			System.out.println("*******************");
		}
	}

	public void imprimirDepartamentos(ArrayList<Departamento> dptos) {
		if (dptos != null) {
			System.out.println("*******************");
			System.out.println("*  DEPARTAMENTOS  *");
			System.out.println("*******************");
			for (Departamento d : dptos) {
				imprimirDepartamento(d);
			}
		} else {
			System.out.println("> No se han encontrado departamentos.");
		}
	}

	public void imprimirEmpleado(Empleado e, int formatToString) {
		if (e != null) {
			switch (formatToString) {
			case 2:
				System.out.println(e.toStringEjer2());
				break;
			case 3:
				System.out.println(e.toStringEjer3());
				break;
			default: 
				System.out.println(e.toString());
				break;
			}
			System.out.println("*******************");
		}
	}

	public void imprimirEmpleados(ArrayList<Empleado> empleados, int formatToString) {
		if (empleados != null) {
			System.out.println("*******************");
			System.out.println("*    EMPLEADOS    *");
			System.out.println("*******************");
			for (Empleado e : empleados) {
				imprimirEmpleado(e, formatToString);
			}
		}
	}

}
