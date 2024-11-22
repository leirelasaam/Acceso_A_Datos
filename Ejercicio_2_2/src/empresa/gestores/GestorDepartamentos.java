package empresa.gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import empresa.modelo.Departamento;
import empresa.utils.DBUtils;

public class GestorDepartamentos {
	private static final String SELECT_ALL = "SELECT * FROM departamentos;";
	
	public ArrayList<Departamento> buscarTodosLosDepartamentos() {
		ArrayList<Departamento> ret = null;
		String sql = SELECT_ALL;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				if (null == ret)
					ret = new ArrayList<Departamento>();

				Departamento d = new Departamento();
				d.setDept_no(resultSet.getInt("dept_no"));
				d.setDnombre(resultSet.getString("dnombre"));
				d.setLoc(resultSet.getString("loc"));

				ret.add(d);
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
