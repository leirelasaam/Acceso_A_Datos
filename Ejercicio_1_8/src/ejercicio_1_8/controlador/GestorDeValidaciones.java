package ejercicio_1_8.controlador;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.JTextField;

/**
 * Gestiona la validación de los campos.
 */
public class GestorDeValidaciones {
	private static final int MAX_CARACTERES = 20;
	private static final int MAX_GOLES = 99;
	
	/**
	 * Valida que los campos no estén vacíos, ya que son obligatorios.
	 * 
	 * @param campos Campos de texto a validar
	 * @return True si no están vacíos y False en caso contrario.
	 */
	public boolean validarCamposVacios(ArrayList<JTextField> campos) {
		for (JTextField campo : campos) {
			if (campo.getText().isEmpty()) {
				Utils.mostrarError("Los campos no pueden estar vacíos.");
				return false;
			}
		}
		return true;
	}

	/**
	 * Valida que los campos de los nombres de los equipos y el lugar no sobrepasen
	 * el límite de caracteres.
	 * 
	 * @param campos Campos de texto a validar
	 * @return True si no sobrepasan el límite y False en caso contrario.
	 */
	public boolean validarLongitud(ArrayList<JTextField> campos) {
		for (JTextField campo : campos) {
			if (campo.getText().length() > MAX_CARACTERES) {
				Utils.mostrarError("Los nombres de los equipos y el lugar deben tener un máximo de " + MAX_CARACTERES
						+ " caracteres.");
				return false;
			}
		}
		return true;
	}

	/**
	 * Valida que la fecha esté en el formato correcto.
	 * 
	 * @param campo Campo de texto que contiene la fecha
	 * @return True si el formato de fecha es correcto y False en caso contrario.
	 */
	public boolean validarFecha(JTextField campo) {
		try {
			String fechaStr = campo.getText();
			Utils.stringToLocalDate(fechaStr);
		} catch (DateTimeParseException e) {
			Utils.mostrarError("Formato de fecha incorrecto.");
			return false;
		}
		return true;
	}

	/**
	 * Valida que los goles tengan un valor numérico y no sobrepasen el límite
	 * establecido.
	 * 
	 * @param campoLocal Campo de texto que contiene los goles del equipo local
	 * @param campoVisitante Campo de texto que contiene los goles del equipo visitante
	 * @return True si pasan la validación y False en caso contrario.
	 */
	public boolean validarGoles(JTextField campoLocal, JTextField campoVisitante) {
		try {
			int golesLocal = Utils.stringToInt(campoLocal.getText());
			int golesVisitante = Utils.stringToInt(campoVisitante.getText());

			if (!validarGoles(golesLocal) || !validarGoles(golesVisitante)) {
				Utils.mostrarError("Los goles deben ser entre 0 y " + MAX_GOLES + ".");
				return false;
			}
		} catch (NumberFormatException e) {
			Utils.mostrarError("Los goles deben tener un valor numérico.");
			return false;
		}
		return true;
	}

	/**
	 * Valida que los goles tengan un máximo de 2 dígitos y que el valor mínimo sea
	 * 0.
	 * 
	 * @param goles Número entero que indica la cantidad de goles
	 * @return True si pasa la validación y False si no.
	 */
	public boolean validarGoles(int goles) {
		if (goles < 0 || goles > MAX_GOLES) {
			return false;
		}

		return true;
	}
}
