package ejercicio_1_10.controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ejercicio_1_10.modelo.Producto;

public class GestorDeXML {
	private static final String FICHERO_ORIGINAL = "src//ejercicio_1_10//tienda.xml";

	public int obtenerCantidadVentas(String nomDepar) throws FileNotFoundException, ParserConfigurationException,
			SAXException, IOException, XPathExpressionException {
		int cantidad = 0;
		File archivo = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;

		XPath xPath = XPathFactory.newInstance().newXPath();

		try {
			archivo = new File(FICHERO_ORIGINAL);
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(archivo);
			doc.getDocumentElement().normalize();
			String expresionDepId = "string(//dpto[nombre='" + nomDepar + "']/@id)";
			String depId = (String) xPath.evaluate(expresionDepId, doc, XPathConstants.STRING);

			String expresionVentas = "//venta[@id='" + depId + "']";
			NodeList nodeList = (NodeList) xPath.evaluate(expresionVentas, doc, XPathConstants.NODESET);

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node nNode = nodeList.item(i);
				String cantidadVenta = null;

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					cantidadVenta = eElement.getElementsByTagName("cantidad").item(0).getTextContent();
				}

				int cant = 0;
				try {
					cant = Integer.parseInt(cantidadVenta);
				} catch (Exception e) {

				}

				cantidad += cant;
			}

		} catch (FileNotFoundException e) {
			throw e;
		} catch (ParserConfigurationException e) {
			throw e;
		} catch (SAXException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (XPathExpressionException e) {
			throw e;
		}

		return cantidad;
	}

	public ArrayList<Producto> obtenerProductosPorDepartamento(String nomDepar) throws FileNotFoundException,
			ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		ArrayList<Producto> productos = null;
		File archivo = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;

		XPath xPath = XPathFactory.newInstance().newXPath();

		try {
			archivo = new File(FICHERO_ORIGINAL);
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(archivo);
			doc.getDocumentElement().normalize();
			String expresionDepId = "string(//dpto[nombre='" + nomDepar + "']/@id)";
			String depId = (String) xPath.evaluate(expresionDepId, doc, XPathConstants.STRING);

			String expresionProductos = "//producto[@venta='" + depId + "']";
			NodeList nodeList = (NodeList) xPath.evaluate(expresionProductos, doc, XPathConstants.NODESET);

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node nNode = nodeList.item(i);
				String nombre = null;
				String precio = null;
				String id = null;

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					nombre = eElement.getElementsByTagName("nombre").item(0).getTextContent();
					precio = eElement.getElementsByTagName("precio").item(0).getTextContent();
					id = eElement.getAttribute("id");
				}

				if (productos == null)
					productos = new ArrayList<Producto>();

				productos.add(new Producto(id, depId, nombre, precio));
			}

		} catch (FileNotFoundException e) {
			throw e;
		} catch (ParserConfigurationException e) {
			throw e;
		} catch (SAXException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (XPathExpressionException e) {
			throw e;
		}

		return productos;
	}

	public ArrayList<Producto> obtenerProductosPorCantidad(int cantidad) throws FileNotFoundException,
			ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		ArrayList<Producto> productos = null;
		File archivo = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;

		XPath xPath = XPathFactory.newInstance().newXPath();

		try {
			archivo = new File(FICHERO_ORIGINAL);
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(archivo);
			doc.getDocumentElement().normalize();

			// Listado de IDs de los productos cuya cantidad en ventas sea X
			String expresionProductos = "//venta[cantidad='" + cantidad + "']/producto/text()";
			NodeList nodeList = (NodeList) xPath.evaluate(expresionProductos, doc, XPathConstants.NODESET);

			for (int i = 0; i < nodeList.getLength(); i++) {
				String productId = nodeList.item(i).getNodeValue();

				String expressionProducto = "//producto[@id='" + productId + "']";
				NodeList nodeProducto = (NodeList) xPath.evaluate(expressionProducto, doc, XPathConstants.NODESET);

				// Recorrer los elementos dentro de cada nodo Producto
				for (int j = 0; j < nodeProducto.getLength(); j++) {
					Node nNode = nodeProducto.item(j);
					String nombre = null;
					String precio = null;
					String id = null;
					String venta = null;

					if (nNode != null && nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						nombre = eElement.getElementsByTagName("nombre").item(0).getTextContent();
						precio = eElement.getElementsByTagName("precio").item(0).getTextContent();
						id = eElement.getAttribute("id");
						venta = eElement.getAttribute("venta");
					}

					if (productos == null)
						productos = new ArrayList<Producto>();

					boolean existe = false;
					for (Producto producto : productos) {
						if (producto.getId().equals(id)) {
							existe = true; // El producto ya existe
							break;
						}
					}

					Producto producto = new Producto(id, venta, nombre, precio);
					if (producto != null && !existe)
						productos.add(producto);
				}
			}

		} catch (FileNotFoundException e) {
			throw e;
		} catch (ParserConfigurationException e) {
			throw e;
		} catch (SAXException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (XPathExpressionException e) {
			throw e;
		}

		return productos;
	}

	public String obtenerResponsablePorNombreProducto(String nombreProducto) throws FileNotFoundException,
			ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		String nombre = null;
		File archivo = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;

		XPath xPath = XPathFactory.newInstance().newXPath();

		try {
			archivo = new File(FICHERO_ORIGINAL);
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(archivo);
			doc.getDocumentElement().normalize();

			String expresionDepId = "string(//producto[nombre='" + nombreProducto + "']/@venta)";

			String depId = (String) xPath.evaluate(expresionDepId, doc, XPathConstants.STRING);

			String expresionResponsable = "//dpto[@id='" + depId + "']/responsable/text()";
			nombre = (String) xPath.evaluate(expresionResponsable, doc, XPathConstants.STRING);

		} catch (FileNotFoundException e) {
			throw e;
		} catch (ParserConfigurationException e) {
			throw e;
		} catch (SAXException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (XPathExpressionException e) {
			throw e;
		}

		return nombre;
	}

	public ArrayList<String> obtenerResponsablesPorFechaVenta(String fecha) throws FileNotFoundException,
			ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		ArrayList<String> responsables = null;
		File archivo = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;

		XPath xPath = XPathFactory.newInstance().newXPath();

		try {
			archivo = new File(FICHERO_ORIGINAL);
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(archivo);
			doc.getDocumentElement().normalize();

			String expresionVentas = "//venta[data='" + fecha + "']";
			NodeList nodeList = (NodeList) xPath.evaluate(expresionVentas, doc, XPathConstants.NODESET);
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node nNode = nodeList.item(i);
				String depId = null;
				String pId = null;

				if (nNode != null && nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					pId = eElement.getElementsByTagName("producto").item(0).getTextContent();
					depId = eElement.getAttribute("id");
				}
				
				String expresionResponsable = "//dpto[@id='" + depId + "']/responsable/text()";
				String nomResp = (String) xPath.evaluate(expresionResponsable, doc, XPathConstants.STRING);

				if (responsables == null)
					responsables = new ArrayList<String>();
				
				responsables.add("Responsable del producto con id " + pId + " - " + nomResp);
			}

		} catch (FileNotFoundException e) {
			throw e;
		} catch (ParserConfigurationException e) {
			throw e;
		} catch (SAXException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (XPathExpressionException e) {
			throw e;
		}

		return responsables;
	}

}
