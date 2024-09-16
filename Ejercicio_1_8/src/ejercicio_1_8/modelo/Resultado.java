package ejercicio_1_8.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Resultado implements Serializable {

	private static final long serialVersionUID = -1446739639477355407L;
	
	private String equipoLocal = null;
	private String equipoVisitante = null;

	private int golesLocal = 0;
	private int golesVisitante = 0;

	private String lugar = null;
	private LocalDate fecha = null;

	public Resultado(String equipoLocal, String equipoVisitante, int golesLocal, int golesVisitante, String lugar,
			LocalDate fecha) {
		super();
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
		this.golesLocal = golesLocal;
		this.golesVisitante = golesVisitante;
		this.lugar = lugar;
		this.fecha = fecha;
	}

	public String getEquipoLocal() {
		return equipoLocal;
	}

	public void setEquipoLocal(String equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public String getEquipoVisitante() {
		return equipoVisitante;
	}

	public void setEquipoVisitante(String equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	public int getGolesLocal() {
		return golesLocal;
	}

	public void setGolesLocal(int golesLocal) {
		this.golesLocal = golesLocal;
	}

	public int getGolesVisitante() {
		return golesVisitante;
	}

	public void setGolesVisitante(int golesVisitante) {
		this.golesVisitante = golesVisitante;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	@Override
	public int hashCode() {
		return Objects.hash(equipoLocal, equipoVisitante, fecha, golesLocal, golesVisitante, lugar);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resultado other = (Resultado) obj;
		return Objects.equals(equipoLocal, other.equipoLocal) && Objects.equals(equipoVisitante, other.equipoVisitante)
				&& Objects.equals(fecha, other.fecha) && golesLocal == other.golesLocal
				&& golesVisitante == other.golesVisitante && Objects.equals(lugar, other.lugar);
	}

	@Override
	public String toString() {
		return "Resultado [equipoLocal=" + equipoLocal + ", equipoVisitante=" + equipoVisitante + ", golesLocal="
				+ golesLocal + ", golesVisitante=" + golesVisitante + ", lugar=" + lugar + ", fecha=" + fecha + "]";
	}

}
