package empresa.gestores;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import empresa.modelo.Departamento;
import empresa.modelo.Empleado;
import empresa.utils.DBUtils;

/**
 * Gestiona las consultas sobre la tabla empleados de la bbdd.
 */
public class GestorEmpleados {
	// Recoger todos los datos de los empleados junto con la información de su
	// departamento, filtrando por número de departamento.
	private static final String SELECT_ALL_WHERE_DEPT_NO = "SELECT E.*, D.dnombre, D.loc FROM empleados E INNER JOIN departamentos D ON E.dept_no = D.dept_no WHERE E.dept_no = ?;";
	// Ejecutar el procedimiento, que contiene la misma query que
	// SELECT_ALL_WHERE_DEPT_NO, donde el parámetro es el dept_no
	private static final String PROC_EMPLEADOS = "CALL EmpleadosPorDeptNo(?);";

	// Recoge todos los datos del empleado con mayor salario, incluídos los datos
	// del departamento.
	private static final String SELECT_WHERE_MAX_SAL = "SELECT E.*, D.dnombre, D.loc FROM empleados E INNER JOIN departamentos D ON E.dept_no = D.dept_no WHERE E.salario = (SELECT MAX(salario) FROM empleados)";
	// Ejecutar el procedimiento, que contiene la misma query que
	// SELECT_WHERE_MAX_SAL
	private static final String PROC_SALMAX = "CALL MaxSalEmple();";

	/**
	 * Obtiene todos los empleados de un departamento.
	 * 
	 * @param dept_no         Número del departamento.
	 * @param esProcedimiento Booleano que indica la forma en la que se debe
	 *                        ejecutar la sentencia. Si es true se ejecuta un
	 *                        procedimiento almacenado, mientras que si es false, se
	 *                        ejecuta una query.
	 * @return Lista de los empleados con todos sus datos.
	 */
	public ArrayList<Empleado> buscarEmpleadosPorDepartamento(int dept_no, boolean esProcedimiento) {
		ArrayList<Empleado> ret = null;

		// Seleccionar la sql a ejecutar, dependiendo de si queremos ejecutar como
		// procedimiento o no
		String sql = (esProcedimiento) ? PROC_EMPLEADOS : SELECT_ALL_WHERE_DEPT_NO;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			if (esProcedimiento) {
				statement = connection.prepareCall(sql);
				((CallableStatement) statement).setInt(1, dept_no);
				resultSet = ((CallableStatement) statement).executeQuery();
			} else {
				statement = connection.prepareStatement(sql);
				((PreparedStatement) statement).setInt(1, dept_no);
				resultSet = ((PreparedStatement) statement).executeQuery();
			}

			while (resultSet.next()) {

				if (null == ret)
					ret = new ArrayList<Empleado>();

				Empleado e = new Empleado();
				e.setEmp_no(resultSet.getInt("emp_no"));
				e.setApellido(resultSet.getString("apellido"));
				e.setOficio(resultSet.getString("oficio"));
				e.setDir(resultSet.getInt("dir"));
				java.sql.Date fecha = resultSet.getDate("fecha_alt");
				e.setFecha_alt(new java.sql.Date(fecha.getTime()));
				e.setSalario(resultSet.getDouble("salario"));
				e.setComision(resultSet.getDouble("comision"));

				Departamento d = new Departamento();
				d.setDept_no(resultSet.getInt("dept_no"));
				d.setDnombre(resultSet.getString("dnombre"));
				d.setLoc(resultSet.getString("loc"));

				e.setDepartamento(d);

				ret.add(e);
			}
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				// Ignore
			}
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
				// Ignore
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// Ignore
			}
		}
		return ret;
	}

	/**
	 * Obtiene el empleado con el mayor salario de la empresa.
	 * 
	 * @param esProcedimiento Booleano que indica la forma en la que se debe
	 *                        ejecutar la sentencia. Si es true se ejecuta un
	 *                        procedimiento almacenado, mientras que si es false, se
	 *                        ejecuta una query.
	 * @return Empleado con salario máximo de la empresa.
	 */
	public Empleado buscarConMaxSalario(boolean esProcedimiento) {
		Empleado ret = null;
		String sql = (esProcedimiento) ? PROC_SALMAX : SELECT_WHERE_MAX_SAL;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			if (esProcedimiento) {
				statement = connection.prepareCall(sql);
				resultSet = ((CallableStatement) statement).executeQuery();
			} else {
				statement = connection.prepareStatement(sql);
				resultSet = ((PreparedStatement) statement).executeQuery();
			}

			if (resultSet.next()) {
				ret = new Empleado();
				ret.setEmp_no(resultSet.getInt("emp_no"));
				ret.setApellido(resultSet.getString("apellido"));
				ret.setOficio(resultSet.getString("oficio"));
				ret.setDir(resultSet.getInt("dir"));
				java.sql.Date fecha = resultSet.getDate("fecha_alt");
				ret.setFecha_alt(new java.sql.Date(fecha.getTime()));
				ret.setSalario(resultSet.getDouble("salario"));
				ret.setComision(resultSet.getDouble("comision"));

				Departamento d = new Departamento();
				d.setDept_no(resultSet.getInt("dept_no"));
				d.setDnombre(resultSet.getString("dnombre"));
				d.setLoc(resultSet.getString("loc"));

				ret.setDepartamento(d);
			}

		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				// Ignore
			}
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
				// Ignore
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// Ignore
			}
		}
		return ret;
	}

}
