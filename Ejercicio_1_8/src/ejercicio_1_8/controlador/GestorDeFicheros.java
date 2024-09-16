package ejercicio_1_8.controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import ejercicio_1_8.modelo.Resultado;

public class GestorDeFicheros {
	// Ruta relativa del fichero
	private static final String RUTA_MENSAJES_TXT = "src\\ejercicio_1_8\\Resultado.txt";
	
	public GestorDeFicheros() {
		// Constructor vacío
	}

	public void escribir(Resultado resultado) throws FileNotFoundException, IOException {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;

		try {
			File fichero = new File(RUTA_MENSAJES_TXT);
			fos = new FileOutputStream(fichero, true);
			out = new ObjectOutputStream(fos);
			out.writeObject(resultado);
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (null != out)
					out.close();
			} catch (IOException e) {
			}
			try {
				if (null != fos)
					fos.close();
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
