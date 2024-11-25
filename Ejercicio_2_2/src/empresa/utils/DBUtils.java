package empresa.utils;

/**
 * Contiene los datos para manejar la conexión de la bbdd.
 */
public class DBUtils {

	private static final String DB = "leire";
	private static final String PORT = "3305";
	public static final String URL = "jdbc:mysql://localhost:" + PORT + "/" + DB;
	public static final String USER = "root";
	public static final String PASS = "Elorrieta00";
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
}
