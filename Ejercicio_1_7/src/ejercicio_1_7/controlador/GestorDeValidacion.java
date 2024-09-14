package ejercicio_1_7.controlador;

import javax.swing.JOptionPane;

public class GestorDeValidacion {

	public boolean validarConExpresion(String valor, String regex, String mensajeError) {
		if (valor == null || !valor.matches(regex)) {
			JOptionPane.showMessageDialog(null, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public boolean sonCamposValidos(String anio, String mes, String dia, String hora, String minuto, String de,
			String para, String asunto, String contenido) {
		if (!validarConExpresion(anio, "\\d{4}", "El año debe ser una cadena de 4 dígitos."))
			return false;

		if (!validarConExpresion(mes, "\\d{1,2}", "El mes debe ser un número entre 1 y 12.")
				|| Integer.parseInt(mes) < 1 || Integer.parseInt(mes) > 12)
			return false;

		if (!validarConExpresion(dia, "\\d{1,2}", "El día debe ser un número entre 1 y 31.")
				|| Integer.parseInt(dia) < 1 || Integer.parseInt(dia) > 31)
			return false;

		if (!validarConExpresion(hora, "\\d{1,2}", "La hora debe ser un número entre 0 y 23.")
				|| Integer.parseInt(hora) < 0 || Integer.parseInt(hora) > 23)
			return false;

		if (!validarConExpresion(minuto, "\\d{1,2}", "Los minutos deben ser un número entre 0 y 59.")
				|| Integer.parseInt(minuto) < 0 || Integer.parseInt(minuto) > 59)
			return false;

		if (de == null || de.isBlank() || para == null || para.isBlank() || asunto == null || asunto.isBlank()
				|| contenido == null || contenido.isBlank()) {
			JOptionPane.showMessageDialog(null, "Hay campos vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

}
