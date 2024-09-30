package ejercicio_1_10.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Departamento implements Serializable {
	private static final long serialVersionUID = -8035406418701757799L;
	private String id = null;
	private String nombre = null;
	private String responsable = null;

	public Departamento() {
		// Constructor vacío
	}

	public Departamento(String id, String nombre, String responsable) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.responsable = responsable;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, responsable);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(responsable, other.responsable);
	}

	@Override
	public String toString() {
		return "Departamento [id=" + id + ", nombre=" + nombre + ", responsable=" + responsable + "]";
	}

}