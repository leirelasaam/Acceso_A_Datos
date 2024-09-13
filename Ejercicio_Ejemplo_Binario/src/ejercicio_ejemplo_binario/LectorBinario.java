package ejercicio_ejemplo_binario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LectorBinario {
	private static final String RUTA_FICHERO = "C:\\Users\\in2dm3-v\\Documents\\LEIRE_DAM2\\Acceso_A_Datos\\Ejercicio_Ejemplo_Binario\\src\\ejercicio_ejemplo_binario\\archivo.dat";
	private static final String RUTA_FICHERO_LOG = "C:\\Users\\in2dm3-v\\Documents\\LEIRE_DAM2\\Acceso_A_Datos\\Ejercicio_Ejemplo_Binario\\src\\ejercicio_ejemplo_binario\\log.txt";
	
	public static void main(String[] args) {
		Funciones fun = new Funciones();
		
		//String password = "Caraculo1996";
		//String md5 = "1eac93cd02a9f7c40f733c4bcd50ee7a";
		
		Scanner teclado = new Scanner(System.in);
		System.out.print("Introduce tu contraseña: ");
		String input = teclado.next();
		teclado.close();
		
		String md5Fichero = fun.devolverPassMD5deFichero(RUTA_FICHERO);
		String inputEncriptado = fun.encriptarMD5(input);
		
		if (md5Fichero.equals(inputEncriptado)) {
			System.out.println("Bienvenid@");
		} else {
			System.out.println("Error en el login.");
			LocalDate fecha = LocalDate.now();
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
			String hora = LocalTime.now().format(dtf);
			
			String fechaHora = fecha + " " + hora;
			
			String error = "Intento fallido de login";
			fun.escribirFichero(RUTA_FICHERO_LOG, error + " - " + fechaHora, true);
		}
		
	}

	
}
