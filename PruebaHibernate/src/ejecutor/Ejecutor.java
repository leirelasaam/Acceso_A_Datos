package ejecutor;

import java.sql.Date;

import org.hibernate.SessionFactory;

import gestor.GestorDepartamentos;
import gestor.GestorEmpleados;
import utils.HibernateUtil;

public class Ejecutor {

	public static void main(String[] args) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		GestorEmpleados ge = new GestorEmpleados(sesion);
		GestorDepartamentos gd = new GestorDepartamentos(sesion);
		
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
		
		// Cerrar la sesión
		sesion.close();
	}

}
