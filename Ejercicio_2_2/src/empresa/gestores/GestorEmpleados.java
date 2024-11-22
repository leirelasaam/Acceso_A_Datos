package empresa.gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import empresa.modelo.Empleado;
import empresa.utils.DBUtils;

public class GestorEmpleados {

	private static final String SELECT_ALL_WHERE_DEPT_NO = "SELECT * FROM empleados WHERE dept_no = ?;";
	private static final String SELECT_WHERE_MAX_SAL = 
			"SELECT * "
			+ "FROM empleados "
			+ "WHERE salario = ("
			+ "SELECT MAX(salario) "
			+ "FROM empleados"
			+ ");";

	public ArrayList<Empleado> buscarEmpleadosPorDepartamento(int dept_no) {
		ArrayList<Empleado> ret = null;
		String sql = SELECT_ALL_WHERE_DEPT_NO;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, dept_no);
			resultSet = preparedStatement.executeQuery();

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

				ret.add(e);
			}
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No hace falta
			}
		}
		return ret;
	}
	
	public Empleado buscarConMaxSalario() {
		Empleado ret = null;
		String sql = SELECT_WHERE_MAX_SAL;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
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
			}
			
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No hace falta
			}
		}
		return ret;
	}

}
