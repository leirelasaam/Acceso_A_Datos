package ejercicio_1_9.modelo;

import java.io.Serializable;
import java.util.Objects;

public class CD implements Serializable {

	private static final long serialVersionUID = -8923584005078397179L;
	private String titulo = null;
	private String artista = null;
	private String pais = null;
	private String sello = null;
	private double precio = 0;
	private int anio = 0;
	
	public CD() {
		// Constructor vacío
	}

	public CD(String titulo, String artista, String pais, String sello, double precio, int anio) {
		super();
		this.titulo = titulo;
		this.artista = artista;
		this.pais = pais;
		this.sello = sello;
		this.precio = precio;
		this.anio = anio;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getSello() {
		return sello;
	}

	public void setSello(String sello) {
		this.sello = sello;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anio, artista, pais, precio, sello, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CD other = (CD) obj;
		return anio == other.anio && Objects.equals(artista, other.artista) && Objects.equals(pais, other.pais)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio)
				&& Objects.equals(sello, other.sello) && Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
		return "CD [titulo=" + titulo + ", artista=" + artista + ", pais=" + pais + ", sello=" + sello + ", precio="
				+ precio + ", anio=" + anio + "]";
	}

}
