package empresa.utils;

/**
 * Contiene los datos para manejar la conexión de la bbdd.
 */
public class DBUtils {

	private static final String DB = "leire";
	private static final String PORT = "3305";
	// La última parte (?autoReconnect=true&useSSL=false) es para que no salgan los errores relacionados con la conexión SSL
	public static final String URL = "jdbc:mysql://localhost:" + PORT + "/" + DB + "?autoReconnect=true&useSSL=false";
	public static final String USER = "root";
	public static final String PASS = "Elorrieta00";
}
