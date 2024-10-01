package ejercicio_1_9;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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
			ArrayList<CD> cds = gdxml.leer();
			if (cds == null) {
				System.out.println("> No hay CDs en el archivo.");
			} else {
				System.out.println("> Listado de CDs:");
				for (int i = 0; i < cds.size(); i++) {
					CD cd = cds.get(i);
					System.out.println("CD: " + i);
					System.out.println(cd.toStringFormateado());
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("> Error lectura: " + e);
		} catch (ParserConfigurationException e) {
			System.out.println("> Error lectura: " + e);
		} catch (SAXException e) {
			System.out.println("> Error lectura: " + e);
		} catch (IOException e) {
			System.out.println("> Error lectura: " + e);
		}

		try {
			CD cd = new CD("Corazón cromado", "Sen Senra", "Spain", "Universal Music Spain S.L.", "10", "2021");
			gdxml.escribir(cd);
			System.out.println("> Se ha añadido el siguiente CD: ");
			System.out.println("CD:");
			System.out.println(cd.toStringFormateado());
		} catch (FileNotFoundException e) {
			System.out.println("> Error escritura: " + e);
		} catch (TransformerConfigurationException e) {
			System.out.println("> Error escritura: " + e);
		} catch (ParserConfigurationException e) {
			System.out.println("> Error escritura: " + e);
		} catch (SAXException e) {
			System.out.println("> Error escritura: " + e);
		} catch (IOException e) {
			System.out.println("> Error escritura: " + e);
		} catch (TransformerException e) {
			System.out.println("> Error escritura: " + e);
		}

		try {
			gdxml.asignarNumero();
			System.out.println("> Se han asignado números a cada CD.");
		} catch (FileNotFoundException e) {
			System.out.println("> Error adición numérica: " + e);
		} catch (TransformerConfigurationException e) {
			System.out.println("> Error adición numérica: " + e);
		} catch (ParserConfigurationException e) {
			System.out.println("> Error adición numérica: " + e);
		} catch (SAXException e) {
			System.out.println("> Error adición numérica: " + e);
		} catch (IOException e) {
			System.out.println("> Error adición numérica: " + e);
		} catch (TransformerException e) {
			System.out.println("> Error adición numérica: " + e);
		}
	}

}
