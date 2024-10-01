package ejercicio_1_10.controlador;

import java.util.ArrayList;

import ejercicio_1_10.modelo.Producto;

public class Controlador {
	
    private GestorDeXML gdx;

    // Constructor que recibe el GestorDeXML
    public Controlador(GestorDeXML gdx) {
        this.gdx = gdx;
    }
	
    // Método que agrupa el manejo de excepciones
    public void manejarExcepcion(Exception e) {
        System.out.println("> Error: " + e.getMessage());
    }

    // Ejercicio 1
    public void ejecutarEjercicio1() {
        String nombreDepartamento = "Carnicería";
        try {
            int ventas = gdx.obtenerCantidadVentas(nombreDepartamento);
            if (ventas > 0) {
                System.out.println("> Ventas totales en " + nombreDepartamento + ": " + ventas);
            } else {
                System.out.println("> No se han encontrado ventas del departamento " + nombreDepartamento);
            }
        } catch (Exception e) {
            manejarExcepcion(e);
        }
    }

    // Ejercicio 2
    public void ejecutarEjercicio2() {
        String nombreDepartamento = "Carnicería";
        try {
            ArrayList<Producto> productos = gdx.obtenerProductosPorDepartamento(nombreDepartamento);
            if (productos != null) {
                System.out.println("> Precios de los productos de " + nombreDepartamento + ":");
                for (Producto producto : productos) {
                    System.out.println(producto.toStringPrecio());
                }
            } else {
                System.out.println("> No se han encontrado productos del departamento " + nombreDepartamento);
            }
        } catch (Exception e) {
            manejarExcepcion(e);
        }
    }

    // Ejercicio 3
    public void ejecutarEjercicio3() {
        int cantidad = 3;
        try {
            ArrayList<Producto> productos = gdx.obtenerProductosPorCantidad(cantidad);
            if (productos != null) {
                System.out.println("> Productos que tengan una cantidad en venta de " + cantidad + ":");
                for (Producto producto : productos) {
                    System.out.println(producto.toStringPrecio());
                }
            } else {
                System.out.println("> No se han encontrado productos con la cantidad en venta de " + cantidad);
            }
        } catch (Exception e) {
            manejarExcepcion(e);
        }
    }

    // Ejercicio 4
    public void ejecutarEjercicio4() {
        String nombreProducto = "Naranjas";
        try {
            String responsable = gdx.obtenerResponsable(nombreProducto, false);
            if (responsable != null) {
                System.out.println("> Responsable del producto " + nombreProducto + ": " + responsable);
            } else {
                System.out.println("> No se ha encontrado al responsable del producto " + nombreProducto);
            }
        } catch (Exception e) {
            manejarExcepcion(e);
        }
    }
    
    // Ejercicio 5
    public void ejecutarEjercicio5() {
        String fecha = "2013/3/10";
        try {
            String responsable = gdx.obtenerResponsable(fecha, true);
            if (responsable != null) {
                System.out.println("> Responsable de la venta del " + fecha + ": " + responsable);
            } else {
                System.out.println("> No se ha encontrado al responsable de la venta del " + fecha);
            }
        } catch (Exception e) {
            manejarExcepcion(e);
        }
    }

}
