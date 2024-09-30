package ejercicio_1_10.controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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
			String expresionDepId = "string(//dpto[nombre=\"" + nomDepar + "\"]/@id)";
			String depId = (String) xPath.evaluate(expresionDepId, doc, XPathConstants.STRING);

			String expresionVentas = "//venta[@id=\"" + depId.toUpperCase() + "\"]";
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

	public void obtenerPrecios(String nomDepar) throws FileNotFoundException, ParserConfigurationException,
			SAXException, IOException, XPathExpressionException {
		double cantidad = 0;
		File archivo = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;

		XPath xPath = XPathFactory.newInstance().newXPath();

		try {
			archivo = new File(FICHERO_ORIGINAL);
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(archivo);
			doc.getDocumentElement().normalize();
			String expresionDepId = "string(//dpto[nombre=\"" + nomDepar + "\"]/@id)";
			String depId = (String) xPath.evaluate(expresionDepId, doc, XPathConstants.STRING);

			String expresionVentas = "//venta[@id=\"" + depId.toUpperCase() + "\"]";
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

		//return cantidad;
	}

}
