package ejercicio_1_7.controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import ejercicio_1_7.modelo.Mensaje;

public class GestorDeFicheros {
	// Ruta relativa del fichero
	private static final String RUTA_MENSAJES_TXT = "src\\ejercicio_1_7\\Mensajes.txt";
	
	public GestorDeFicheros() {
		// Constructor vacío
	}

	public void escribir(Mensaje mensaje) throws FileNotFoundException, IOException {
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;

		try {
			File fichero = new File(RUTA_MENSAJES_TXT);
			fileWriter = new FileWriter(fichero, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.newLine();
			bufferedWriter.write(mensaje.toStringFormateado());
			

		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (null != bufferedWriter)
					bufferedWriter.close();
			} catch (IOException e) {
			}
			try {
				if (null != fileWriter)
					fileWriter.close();
			} catch (IOException e) {
			}
		}
	}

	public String leer() throws FileNotFoundException, IOException {
		String ret = "";
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		try {
			File fichero = new File(RUTA_MENSAJES_TXT);
			fileReader = new FileReader(fichero);
			bufferedReader = new BufferedReader(fileReader);

			String linea = null;
			while ((linea = bufferedReader.readLine()) != null) {
				ret += linea + "\n";
			}

		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (null != bufferedReader)
					bufferedReader.close();
			} catch (IOException e) {
			}
			try {
				if (null != fileReader)
					fileReader.close();
			} catch (IOException e) {
			}
		}
		return ret;
	}
}
