package ejercicio_1_10;


import ejercicio_1_10.controlador.Controlador;
import ejercicio_1_10.controlador.GestorDeXML;

public class Ejercicio_1_10 {

	public static void main(String[] args) {
		Controlador controlador = new Controlador(new GestorDeXML());

		// Llamadas a los ejercicios
		controlador.ejecutarEjercicio1("Carnicería");
		controlador.ejecutarEjercicio2("Carnicería");
		controlador.ejecutarEjercicio3(3);
		controlador.ejecutarEjercicio4("Naranjas");
		controlador.ejecutarEjercicio5("2013/3/10");
	}
}
