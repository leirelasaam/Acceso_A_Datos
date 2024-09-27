package ejercicio_1_9;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import ejercicio_1_9.controlador.GestorDeXML;
import ejercicio_1_9.modelo.CD;

/**
 * Ejecuta el ejercicio 1.9
 */
public class Ejercicio_1_9 {

	public static void main(String[] args) {
		GestorDeXML gdxml = new GestorDeXML();
		
		try {
			String salida = gdxml.leer();
			System.out.println(salida);
		} catch (FileNotFoundException e) {
			System.out.println("> Error: " + e);
		} catch (ParserConfigurationException e) {
			System.out.println("> Error: " + e);
		} catch (SAXException e) {
			System.out.println("> Error: " + e);
		} catch (IOException e) {
			System.out.println("> Error: " + e);
		}
		
		try {
			gdxml.escribir(new CD("Corazón cromado", "Sen Senra", "Spain", "Universal Music Spain S.L.", 10, 2021));
		} catch (FileNotFoundException e) {
			System.out.println("> Error: " + e);
		} catch (TransformerConfigurationException e) {
			System.out.println("> Error: " + e);
		} catch (ParserConfigurationException e) {
			System.out.println("> Error: " + e);
		} catch (SAXException e) {
			System.out.println("> Error: " + e);
		} catch (IOException e) {
			System.out.println("> Error: " + e);
		} catch (TransformerException e) {
			System.out.println("> Error: " + e);
		}
		
		try {
			gdxml.asignarNumero();
		} catch (FileNotFoundException e) {
			System.out.println("> Error: " + e);
		} catch (TransformerConfigurationException e) {
			System.out.println("> Error: " + e);
		} catch (ParserConfigurationException e) {
			System.out.println("> Error: " + e);
		} catch (SAXException e) {
			System.out.println("> Error: " + e);
		} catch (IOException e) {
			System.out.println("> Error: " + e);
		} catch (TransformerException e) {
			System.out.println("> Error: " + e);
		}
	}

}
