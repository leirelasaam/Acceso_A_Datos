package prueba;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import prueba.entities.Departamento;
import prueba.entities.Empleado;
import prueba.managers.ManagerDepartamentos;
import prueba.managers.ManagerEmpleados;
import prueba.utils.HibernateUtil;

public class Prueba {

	private ManagerEmpleados managerE = null;
	private ManagerDepartamentos managerD = null;

	public Prueba() {
		initialize();
	}

	private void initialize() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		System.out.println("Servicio iniciado.");

		managerE = new ManagerEmpleados(sessionFactory);
		managerD = new ManagerDepartamentos(sessionFactory);
	}

	private void mostrarEmpleados(String titulo) {
		ArrayList<Empleado> empleados = null;
		try {
			empleados = managerE.getAll();
			if (empleados != null)
				System.out.println("***** " + titulo.toUpperCase() + " *****");
			printEmpleados(empleados);

		} catch (HibernateException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void mostrarEmpleadosPorNumDep(String titulo, int deptNo) {
		ArrayList<Empleado> empleados = null;
		try {
			empleados = managerE.getEmpleadosPorDep(deptNo);
			if (empleados != null)
				System.out.println("***** " + titulo.toUpperCase() + " *****");
			printEmpleados(empleados);

		} catch (HibernateException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void mostrarEmpleadoMaxSal(String titulo) {
		Empleado empleado = null;
		try {
			empleado = managerE.getEmpleMaxSal();
			if (empleado != null)
				System.out.println("***** " + titulo.toUpperCase() + " *****");
			printEmpleado(empleado);

		} catch (HibernateException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void printEmpleado(Empleado e) {
		System.out.println(e.toString());
	}

	private void printEmpleados(ArrayList<Empleado> empleados) {
		if (empleados != null && empleados.size() > 0) {
			for (Empleado e : empleados) {
				printEmpleado(e);
			}
		}
	}
	
	private void mostrarDepartamentosPorNombre(String titulo, String[] nombres) {
		ArrayList<Departamento> departamentos = null;
		try {
			departamentos = managerD.getDeparByNames(nombres);
			if (departamentos != null)
				System.out.println("***** " + titulo.toUpperCase() + " *****");
			printDepartamentos(departamentos);

		} catch (HibernateException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	
	private void printDepartamento(Departamento d) {
		System.out.println(d.toString());
	}

	private void printDepartamentos(ArrayList<Departamento> departamentos) {
		if (departamentos != null && departamentos.size() > 0) {
			for (Departamento d : departamentos) {
				printDepartamento(d);
			}
		}
	}

	public static void main(String[] args) {
		Prueba prueba = new Prueba();
		prueba.mostrarEmpleados("todos los empleados");

		int deptNo = 10;
		prueba.mostrarEmpleadosPorNumDep("empleados del departamento " + deptNo, deptNo);
		
		prueba.mostrarEmpleadoMaxSal("empleado con salario más alto");
		
		String[] dnombres = {"CONTABILIDAD", "INVESTIGACION"};
		prueba.mostrarDepartamentosPorNombre("datos de departamentos", dnombres);
	}

}
