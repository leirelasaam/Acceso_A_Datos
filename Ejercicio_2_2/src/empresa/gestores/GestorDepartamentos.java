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
import empresa.utils.DBUtils;

/**
 * Gestiona las consultas sobre la tabla departamentos de la bbdd.
 */
public class GestorDepartamentos {
	// Recoger todos los datos de los departamentos
	private static final String SELECT_ALL = "SELECT * FROM departamentos;";
	// Ejecutar el procedimiento, que contiene la misma query que SELECT_ALL
	private static final String PROC_DEPARTAMENTOS = "CALL TodosDepartamentos();";

	/**
	 * Obtiene todos los departamentos junto con todos sus datos correspondientes.
	 * 
	 * @param esProcedimiento Booleano que indica la forma en la que se debe
	 *                        ejecutar la sentencia. Si es true se ejecuta un
	 *                        procedimiento almacenado, mientras que si es false, se
	 *                        ejecuta una query.
	 * @return Lista que contiene todos los departamentos.
	 */
	public ArrayList<Departamento> buscarTodosLosDepartamentos(boolean esProcedimiento) {
		ArrayList<Departamento> ret = null;
		// Seleccionar la sql a ejecutar
		String sql = (esProcedimiento) ? PROC_DEPARTAMENTOS : SELECT_ALL;

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			// Castear al statement concreto
			if (esProcedimiento) {
				statement = connection.prepareCall(sql);
				resultSet = ((CallableStatement) statement).executeQuery();
			} else {
				statement = connection.prepareStatement(sql);
				resultSet = ((PreparedStatement) statement).executeQuery();
			}

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
