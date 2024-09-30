package ejercicio_1_10.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Producto implements Serializable {

	private static final long serialVersionUID = -276250862315479939L;
	private String id = null;
	private String venta = null;
	private String nombre = null;
	private double precio = 0;

	public Producto() {
		// Constructor vacío
	}

	public Producto(String id, String venta, String nombre, double precio) {
		super();
		this.id = id;
		this.venta = venta;
		this.nombre = nombre;
		this.precio = precio;
	}
	
	public Producto(String id, String venta, String nombre, String precio) {
		super();
		this.id = id;
		this.venta = venta;
		this.nombre = nombre;
		double precioDouble = 0;
		try {
			precioDouble = Integer.parseInt(precio);
		} catch(Exception e) {
			
		}
		this.precio = precioDouble;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVenta() {
		return venta;
	}

	public void setVenta(String venta) {
		this.venta = venta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, precio, venta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(precio, other.precio) && Objects.equals(venta, other.venta);
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", venta=" + venta + ", nombre=" + nombre + ", precio=" + precio + "]";
	}

}
