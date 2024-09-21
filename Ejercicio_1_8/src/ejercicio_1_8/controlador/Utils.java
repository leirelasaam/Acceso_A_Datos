package ejercicio_1_8.controlador;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 * Clase de utilidades para realizar conversiones y crear mensajes con
 * JOptionPane.
 */
public class Utils {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public static int stringToInt(String texto) throws NumberFormatException {
		return Integer.parseInt(texto);
	}

	/**
	 * Convierte un LocalDate en un String con el formato indicado.
	 * 
	 * @param fecha LocalDate a convertir en String
	 * @return String que contiene la fecha
	 * @throws DateTimeException si no se puede realizar la conversión
	 */
	public static String localDateToString(LocalDate fecha) throws DateTimeException {
		return fecha.format(FORMATTER);
	}

	/**
	 * Convierte un String en LocalDate.
	 * 
	 * @param fecha String que contiene la fecha en el formato indicado
	 * @return LocalDate convertido
	 * @throws DateTimeException si no se puede realizar la conversión
	 */
	public static LocalDate stringToLocalDate(String fecha) throws DateTimeException {
		return LocalDate.parse(fecha, FORMATTER);
	}

	/**
	 * Crea un mensaje de error, mostrándolo en un JOptionPane.
	 * 
	 * @param mensaje String que contiene el cuerpo del mensaje
	 */
	public static void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Crea un mensaje de confirmación, mostrándolo en un JOptionPane.
	 * 
	 * @param titulo  String que contiene el título
	 * @param mensaje String que contiene el cuerpo del mensaje
	 */
	public static void mostrarMensaje(String titulo, String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}

}
