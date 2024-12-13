package gestor;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import pojos.Empleados;

public class GestorEmpleados {

	SessionFactory sesion = null;

	public GestorEmpleados(SessionFactory sesion) {
		this.sesion = sesion;
	}

	public void obtenerEmpleadosPorDeptNo(byte deptNo) {
		Session session = sesion.openSession();
		String hql = "FROM Empleados as E WHERE E.departamentos.deptNo = :deptNo";
		Query q = session.createQuery(hql);
		q.setParameter("deptNo", deptNo);
		List<?> filas = q.list();

		System.out.println("*** EMPLEADOS DEL DEPARTAMENTO " + deptNo + " ***");

		if (filas.size() > 0) {
			for (int i = 0; i < filas.size(); i++) {
				Empleados e = (Empleados) filas.get(i);
				System.out.println(e.toStringFormat1());
			}
		} else {
			System.out.println("> No hay empleados.");
		}

		session.close();
	}

	public void obtenerEmpleadoSalMax() {
		Session session = sesion.openSession();
		String hql = "FROM Empleados as E ORDER BY E.salario DESC";
		Query q = session.createQuery(hql);
		q.setMaxResults(1);
		List<?> filas = q.list();

		System.out.println("*** EMPLEADO CON MAYOR SALARIO ***");

		if (filas.size() > 0) {
			Empleados e = (Empleados) filas.get(0);
			System.out.println(e.toStringFormat2());
			System.out.println(e.getDepartamentos().toStringFormat1());
		} else {
			System.out.println("> No hay empleados.");
		}

		session.close();
	}

	public void obtenerEmpleadosPorDnombreYOficio(String dnombre, String oficio) {
		Session session = sesion.openSession();
		String hql = "FROM Empleados as E WHERE E.departamentos.dnombre = :dnombre AND E.oficio  = :oficio";
		Query q = session.createQuery(hql);
		q.setParameter("dnombre", dnombre).setParameter("oficio", oficio);
		List<?> filas = q.list();

		System.out.println("*** EMPLEADOS DEL DEPARTAMENTO " + dnombre + " Y OFICIO " + oficio + " ***");

		if (filas.size() > 0) {
			for (int i = 0; i < filas.size(); i++) {
				Empleados e = (Empleados) filas.get(i);
				System.out.println(e.toStringFormat2());
			}
		} else {
			System.out.println("> No hay empleados.");
		}

		session.close();
	}

	public void obtenerEmpleadosPorFechAlt(Date fecha) {
		Session session = sesion.openSession();
		String hql = "FROM Empleados as E WHERE E.fechaAlt = :fechaAlt";
		Query q = session.createQuery(hql);
		q.setParameter("fechaAlt", fecha);
		List<?> filas = q.list();

		System.out.println("*** EMPLEADOS CON FECHA DE ALTA " + fecha + " ***");

		if (filas.size() > 0) {
			for (int i = 0; i < filas.size(); i++) {
				Empleados e = (Empleados) filas.get(i);
				System.out.println(e.toStringFormat2());
			}
		} else {
			System.out.println("> No hay empleados.");
		}

		session.close();
	}

	public void obtenerEmpleadosPorLocalidadOrdenadosPorSalario(String loc, int limite) {
		Session session = sesion.openSession();
		
		String limit = null;
		if (limite == 0)
			limit = "";
		else
			limit = " LIMIT " + limite;
		
		String hql = "FROM Empleados as E WHERE E.departamentos.loc = :loc ORDER BY E.salario DESC" + limit;
		Query q = session.createQuery(hql);
		q.setParameter("loc", loc);
		List<?> filas = q.list();

		System.out.println("*** EMPLEADOS DEL DEPARTAMENTO DE " + loc + " ***");

		if (filas.size() > 0) {
			for (int i = 0; i < filas.size(); i++) {
				Empleados e = (Empleados) filas.get(i);
				System.out.println(e.toStringFormat1());
			}
		} else {
			System.out.println("> No hay empleados.");
		}

		session.close();
	}

	public void obtenerDirectorEmpleMaxComision() {
		Session session = sesion.openSession();
		String hql = "FROM Empleados as E WHERE E.empNo = (SELECT EM.dir FROM Empleados as EM WHERE EM.comision IS NOT NULL ORDER BY EM.comision DESC LIMIT 1)";
		Query q = session.createQuery(hql);
		List<?> filas = q.list();
		q.setMaxResults(1);

		System.out.println("*** DIRECTOR DEL EMPLEADO CON MAYOR COMISIÓN ***");

		if (filas.size() > 0) {
			Empleados e = (Empleados) filas.get(0);
			System.out.println(e.toStringFormat1());
		} else {
			System.out.println("> No hay empleados.");
		}

		session.close();
	}
}