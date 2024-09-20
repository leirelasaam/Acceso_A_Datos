package ejercicio;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Ejercicio {

	/*
	 * VALORES DE LOS TIPOS DE DATO: String 2 Int 4 Double 8
	 */
	public static void main(String[] args) {
		Funciones funciones = new Funciones();
		
		try {
			funciones.ejercicio1();
			funciones.ejercicio2();
			funciones.ejercicio3();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EOFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
