package ejercicio_1_7.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Mensaje implements Serializable {

	private static final long serialVersionUID = -84833555997549967L;
	private LocalDate fecha = null;
	private LocalTime hora = null;
	private String remitente = null;
	private String destinatario = null;
	private String asunto = null;
	private String contenido = null;
	
	public Mensaje() {
		// Constructor vacío
	}
	
	public Mensaje(LocalDate fecha, LocalTime hora, String remitente, String destinatario, String asunto,
			String mensaje) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.remitente = remitente;
		this.destinatario = destinatario;
		this.asunto = asunto;
		this.contenido = mensaje;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public LocalTime getHora() {
		return hora;
	}
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String mensaje) {
		this.contenido = mensaje;
	}
	@Override
	public int hashCode() {
		return Objects.hash(asunto, destinatario, fecha, hora, contenido, remitente);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mensaje other = (Mensaje) obj;
		return Objects.equals(asunto, other.asunto) && Objects.equals(destinatario, other.destinatario)
				&& Objects.equals(fecha, other.fecha) && Objects.equals(hora, other.hora)
				&& Objects.equals(contenido, other.contenido) && Objects.equals(remitente, other.remitente);
	}
	@Override
	public String toString() {
		return "Mensaje [fecha=" + fecha + ", hora=" + hora + ", remitente=" + remitente + ", destinatario="
				+ destinatario + ", asunto=" + asunto + ", contenido=" + contenido + "]";
	}
	
	public String toStringFormateado() {
		return "fecha: " + fecha + "\n"
				+ "hora: " + hora + "\n"
				+ "para: " + destinatario + "\n"
				+ "de: " + remitente + "\n"
				+ "asunto: " + asunto + "\n"
				+ "contenido: " + contenido + "\n"
				+ "***********" + "\n";
	}
	
	public String localTimeToString(LocalTime time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(formatter);
        return formattedTime;
	}
	

}
