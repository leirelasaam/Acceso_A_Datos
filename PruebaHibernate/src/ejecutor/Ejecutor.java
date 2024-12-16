package ejecutor;

import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.SessionFactory;

import gestor.GestorDepartamentos;
import gestor.GestorEmpleados;
import utils.HibernateUtil;

public class Ejecutor {

	public static void main(String[] args) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		GestorEmpleados ge = new GestorEmpleados(sesion);
		GestorDepartamentos gd = new GestorDepartamentos(sesion);
		
		System.out.println("***** PARTE 1: Consultas *****");
		
		// Datos de los emepleados por número de departamento
		byte deptNo = 10;
		ge.obtenerEmpleadosPorDeptNo(deptNo);
		System.out.println();
		
		// Empleado con salario máximo
		ge.obtenerEmpleadoSalMax();
		System.out.println();
		
		// Mostrar información de los departamentos, se introducen en el array
		String[] dnombres = {"CONTABILIDAD", "INVESTIGACION"};
		gd.mostrarDepPorNombres(dnombres);
		System.out.println();
		
		// Mostrar empleados de x departamento y oficio
		String dnombre = "CONTABILIDAD";
		String oficio = "DIRECTOR";
		ge.obtenerEmpleadosPorDnombreYOficio(dnombre, oficio);
		
		@SuppressWarnings("deprecation")
		Date fecha = Date.valueOf("1990-12-17");
		ge.obtenerEmpleadosPorFechAlt(fecha);
		System.out.println();
		
		String loc = "MADRID";
		ge.obtenerEmpleadosPorLocalidadOrdenadosPorSalario(loc, 0);
		System.out.println();
		
		ge.obtenerDirectorEmpleMaxComision();
		System.out.println();
		
		loc = "BARCELONA";
		ge.obtenerEmpleadosPorLocalidadOrdenadosPorSalario(loc, 1);
		
		System.out.println("***** PARTE 2: Operaciones *****");
		
		dnombre = "INFORMATICA";
		loc = "BILBAO";
		//gd.insertarDepartamento(dnombre, loc);
		
		String apellido = "LASA";
		dnombre = "INFORMATICA";
		short dir = 0;
		float salario = 2300;
		float comision = 1000;
		fecha = Date.valueOf(LocalDate.now());
		//ge.insertarEmpleado(apellido, oficio, dir, fecha, salario, comision, dnombre);
		
		apellido = "GIL";
		salario = 1300;
		// Fecha de ayer
		fecha = Date.valueOf(LocalDate.now().minusDays(1));
		ge.modificarSalarioYFechaAltPorApellido(apellido, salario, fecha);
		
		System.out.println("*** PRUEBA DE BORRADO ***");
		ge.eliminarPorLocDepartamento("MADRID");
		
		// Cerrar la sesión
		sesion.close();
	}

}
