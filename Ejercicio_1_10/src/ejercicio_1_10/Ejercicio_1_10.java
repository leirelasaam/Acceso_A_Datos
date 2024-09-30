package ejercicio_1_10;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import ejercicio_1_10.controlador.GestorDeXML;

public class Ejercicio_1_10 {

	public static void main(String[] args) {
		GestorDeXML gdx = new GestorDeXML();
		
		int ventas = 0;
		try {
			ventas = gdx.obtenerCantidadVentas("Carnicería");
		} catch (FileNotFoundException e) {
			System.out.println("> Error: " + e);
		} catch (XPathExpressionException e) {
			System.out.println("> Error: " + e);
		} catch (ParserConfigurationException e) {
			System.out.println("> Error: " + e);
		} catch (SAXException e) {
			System.out.println("> Error: " + e);
		} catch (IOException e) {
			System.out.println("> Error: " + e);
		}
		System.out.println("> Ventas en carnicería: " + ventas);

	}

}
