package ejercicio_1_10.controlador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import ejercicio_1_10.modelo.Producto;

/**
 * Clase con funciones para ejecutar los ejercicios correspondientes del Ejercicio 10.
 */
public class Controlador {
	
    private GestorDeXML gdx;
    private static final String FNF_E = "> Error al buscar el archivo XML: "; 
    private static final String XPATH_E = "> Error en la expresión XPath: "; 
    private static final String PARSER_E = "> Error en la configuración del parser XML: "; 
    private static final String SAX_E = "> Error durante el análisis del archivo XML (SAX): "; 
    private static final String IO_E = "> Error de lectura en el archivo XML: "; 

    // Constructor que recibe el GestorDeXML
    public Controlador(GestorDeXML gdx) {
        this.gdx = gdx;
    }
    
    // Manejas mensaje de excepciones
    private void manejarExcepcion(String mensaje, Exception e) {
    	System.out.println(mensaje + e.getMessage());
    }

    // Ejercicio 1
    public void ejecutarEjercicio1(String nombreDepartamento) {
        try {
            int ventas = gdx.obtenerCantidadVentas(nombreDepartamento);
            if (ventas > 0) {
                System.out.println("> Ventas totales en " + nombreDepartamento + ": " + ventas);
            } else {
                System.out.println("> No se han encontrado ventas del departamento " + nombreDepartamento);
            }
		} catch (FileNotFoundException e) {
			manejarExcepcion(FNF_E, e);
		} catch (ParserConfigurationException e) {
			manejarExcepcion(PARSER_E, e);
		} catch (SAXException e) {
			manejarExcepcion(SAX_E, e);
		} catch (IOException e) {
			manejarExcepcion(IO_E, e);
		} catch (XPathExpressionException e) {
			manejarExcepcion(XPATH_E, e);
		}
    }

    // Ejercicio 2
    public void ejecutarEjercicio2(String nombreDepartamento) {
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
		} catch (FileNotFoundException e) {
			manejarExcepcion(FNF_E, e);
		} catch (ParserConfigurationException e) {
			manejarExcepcion(PARSER_E, e);
		} catch (SAXException e) {
			manejarExcepcion(SAX_E, e);
		} catch (IOException e) {
			manejarExcepcion(IO_E, e);
		} catch (XPathExpressionException e) {
			manejarExcepcion(XPATH_E, e);
		}
    }

    // Ejercicio 3
    public void ejecutarEjercicio3(int cantidad) {
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
		} catch (FileNotFoundException e) {
			manejarExcepcion(FNF_E, e);
		} catch (ParserConfigurationException e) {
			manejarExcepcion(PARSER_E, e);
		} catch (SAXException e) {
			manejarExcepcion(SAX_E, e);
		} catch (IOException e) {
			manejarExcepcion(IO_E, e);
		} catch (XPathExpressionException e) {
			manejarExcepcion(XPATH_E, e);
		}
    }

    // Ejercicio 4
    public void ejecutarEjercicio4(String nombreProducto) {
        try {
            String responsable = gdx.obtenerResponsablePorNombreProducto(nombreProducto);
            if (responsable != null) {
                System.out.println("> Responsable del producto " + nombreProducto + ": " + responsable);
            } else {
                System.out.println("> No se ha encontrado al responsable del producto " + nombreProducto);
            }
		} catch (FileNotFoundException e) {
			manejarExcepcion(FNF_E, e);
		} catch (ParserConfigurationException e) {
			manejarExcepcion(PARSER_E, e);
		} catch (SAXException e) {
			manejarExcepcion(SAX_E, e);
		} catch (IOException e) {
			manejarExcepcion(IO_E, e);
		} catch (XPathExpressionException e) {
			manejarExcepcion(XPATH_E, e);
		}
    }
    
    // Ejercicio 5
    public void ejecutarEjercicio5(String fecha) {
        try {
            ArrayList<String> responsables = gdx.obtenerResponsablesPorFechaVenta(fecha);
            if (responsables != null) {
                System.out.println("> Responsables de ventas en el día " + fecha + ": ");
                for (String responsable : responsables) {
                	System.out.println("\t" + responsable);
                }
            } else {
                System.out.println("> No se ha encontrado al responsable de la venta del " + fecha);
            }
		} catch (FileNotFoundException e) {
			manejarExcepcion(FNF_E, e);
		} catch (ParserConfigurationException e) {
			manejarExcepcion(PARSER_E, e);
		} catch (SAXException e) {
			manejarExcepcion(SAX_E, e);
		} catch (IOException e) {
			manejarExcepcion(IO_E, e);
		} catch (XPathExpressionException e) {
			manejarExcepcion(XPATH_E, e);
		}
    }

}
