package ejercicio_1_10.modelo;

import java.util.Objects;

public class Venta {
	private String id = null;
	private String producto = null;
	private int cantidad = 0;

	public Venta() {
		// Constructor vacío
	}

	public Venta(String id, String producto, int cantidad) {
		super();
		this.id = id;
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public Venta(String id, String producto, String cantidad) {
		super();
		this.id = id;
		this.producto = producto;
		int cantidadInt = 0;
		
		try {
			cantidadInt = Integer.parseInt(cantidad);
		} catch(Exception e) {
			
		}
		this.cantidad = cantidadInt;		
				
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cantidad, id, producto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venta other = (Venta) obj;
		return cantidad == other.cantidad && Objects.equals(id, other.id) && Objects.equals(producto, other.producto);
	}

	@Override
	public String toString() {
		return "Venta [id=" + id + ", producto=" + producto + ", cantidad=" + cantidad + "]";
	}

}