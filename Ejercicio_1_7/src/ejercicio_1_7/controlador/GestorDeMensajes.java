package ejercicio_1_7.controlador;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.table.DefaultTableModel;

import ejercicio_1_7.modelo.Mensaje;

public class GestorDeMensajes {

	public void addMessagesToTable(DefaultTableModel model, ArrayList<Mensaje> mensajes) {
		for (Mensaje mensaje : mensajes) {
			model.addRow(new String[] { mensaje.getRemitente(), mensaje.getDestinatario(),
					mensaje.getFecha().toString(), mensaje.localTimeToString(mensaje.getHora()), mensaje.getAsunto(),
					mensaje.getContenido() });
		}
	}

	public ArrayList<Mensaje> parseMessages(String contenidoMsg) throws DateTimeParseException {
		if (contenidoMsg == null)
			return null;
		
		ArrayList<Mensaje> mensajes = new ArrayList<>();

		String[] bloques = contenidoMsg.split("(?<=\\n)\\*{10,}(?=\\n)");

		for (String bloque : bloques) {
			bloque = bloque.trim();
			// Para que no meta al final un bloque vacío
			if (bloque.isEmpty())
				continue;

			String[] lineas = bloque.split("\\n");
			String fechaStr = null;
			String horaStr = null;
			String para = null;
			String de = null;
			String asunto = null;
			String contenido = null;

			for (String linea : lineas) {
				if (linea.startsWith("fecha:")) {
					fechaStr = linea.substring(6).trim();
				} else if (linea.startsWith("hora:")) {
					horaStr = linea.substring(5).trim();
				} else if (linea.startsWith("para:")) {
					para = linea.substring(5).trim();
				} else if (linea.startsWith("de:")) {
					de = linea.substring(3).trim();
				} else if (linea.startsWith("asunto:")) {
					asunto = linea.substring(7).trim();
				} else if (linea.startsWith("contenido:")) {
					contenido = linea.substring(10).trim();
				}
			}

			LocalDate fecha = LocalDate.parse(fechaStr);
			LocalTime hora = LocalTime.parse(horaStr);

			if (fecha != null && hora != null) {
				Mensaje mensaje = new Mensaje(fecha, hora, de, para, asunto, contenido);
				mensajes.add(mensaje);
			}
		}

		return mensajes;
	}
	
	public Mensaje newMessage(String year, String month, String day, String hour, String min, String from, String to,
			String subject, String content) throws DateTimeParseException {
		Mensaje mensaje = null;
		
		LocalDate fecha = null;
		LocalTime hora = null;
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");
		
		if (month.matches("\\d"))
			month = "0" + month;
		if (day.matches("\\d"))
			day = "0" + day;
		
		String fechaStr = year + "-" + month + "-" + day;
		String horaStr = hour + ":" + min;
		
		fecha = LocalDate.parse(fechaStr);
		hora = LocalTime.parse(horaStr, timeFormatter);

		if (fecha != null && hora != null) {
			mensaje = new Mensaje(fecha, hora, from, to, subject, content);
		}
		
		return mensaje;
	}
}
