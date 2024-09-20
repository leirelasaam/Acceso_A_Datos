package ejercicio_1_8;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;

import ejercicio_1_8.controlador.GestorDeFicheros;
import ejercicio_1_8.modelo.Resultado;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestorDeFicheros gf = new GestorDeFicheros();
		
		try {
			//gf.escribir(new Resultado("Real Madrid", "FC Barcelona", 5, 1, "Madrid", LocalDate.now()));
			Resultado resultado = gf.leer();
			System.out.println(resultado);
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
