package ejercicio_1_8.controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import ejercicio_1_8.modelo.Resultado;

/**
 * Gestiona el fichero que se indica en la constante RUTA, de forma que se
 * implementan funciones para su lectura y escritura.
 */
public class GestorDeFicheros {
	// Ruta relativa del fichero
	private static final String RUTA = "src\\ejercicio_1_8\\Resultados.dat";

	public GestorDeFicheros() {
		// Constructor vacío
	}

	/**
	 * Lee el contenido del fichero y devuelve un arraylist con los resultados.
	 * 
	 * @return si hay contenido en el fichero y puede leerlo correctamente, devuelve
	 *         un ArrayList con los resultados. Si no, devuelve un valor nulo.
	 * @throws FileNotFoundException  si no encuentra la ruta del fichero.
	 * @throws IOException            si existe un error durante la lectura
	 * @throws DateTimeParseException si no se puede convertir la fecha
	 */
	public ArrayList<Resultado> leer() throws FileNotFoundException, IOException, DateTimeParseException {
		ArrayList<Resultado> resultados = null;
		Resultado resultado = null;
		File fichero = null;
		DataInputStream dis = null;
		FileInputStream fis = null;
		String eqLocal = null;
		String eqVisitante = null;
		int golesLocal = 0;
		int golesVisitante = 0;
		String lugar = null;
		String fecha = null;
		try {
			fichero = new File(RUTA);
			fis = new FileInputStream(fichero);
			dis = new DataInputStream(fis);

			try {
				while (fis.getChannel().position() < fis.getChannel().size()) {
					eqLocal = dis.readUTF();
					dis.readChar();
					eqVisitante = dis.readUTF();
					dis.readChar();
					golesLocal = dis.readInt();
					dis.readChar();
					golesVisitante = dis.readInt();
					dis.readChar();
					lugar = dis.readUTF();
					dis.readChar();
					fecha = dis.readUTF();
					dis.readChar();

					// Controlar la excepción que puede generar la conversión
					LocalDate fechaDate = null;
					try {
						fechaDate = Utils.stringToLocalDate(fecha);
					} catch (DateTimeParseException e) {
						throw e;
					}

					if (fechaDate != null) {
						resultado = new Resultado(eqLocal, eqVisitante, golesLocal, golesVisitante, lugar, fechaDate);
						if (resultados == null)
							resultados = new ArrayList<Resultado>();
						resultados.add(resultado);
					}
				}
			} catch (EOFException e) {
				// No hacer nada
			}

		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (null != dis)
					dis.close();
			} catch (IOException e) {
			}
			try {
				if (null != fis)
					fis.close();
			} catch (IOException e) {
			}
		}

		return resultados;
	}

	/**
	 * Escribe el resultado en el fichero indicado, separando los campos con un
	 * tabulador y añadiendo al final un salto de línea. No se sobreescribe el
	 * contenido.
	 * 
	 * @param resultado objeto del cual se extraen los datos a escribir
	 * @throws FileNotFoundException  no encuentra el la ruta del archivo
	 * @throws IOException            si hay un error en la escritura
	 * @throws DateTimeParseException si la conversión de fecha a cadena no se puede
	 *                                llevar a cabo
	 */
	public void escribir(Resultado resultado) throws FileNotFoundException, IOException, DateTimeParseException {
		File fichero = null;
		DataOutputStream dos = null;
		FileOutputStream fos = null;

		try {
			fichero = new File(RUTA);
			// Añade el contenido sin sobreescribirlo, al indicar true
			fos = new FileOutputStream(fichero, true);
			dos = new DataOutputStream(fos);

			LocalDate fecha = resultado.getFecha();
			String fechaStr = null;

			// Controlar la excepción que puede generar la conversión
			try {
				fechaStr = Utils.localDateToString(fecha);
			} catch (DateTimeParseException e) {
				throw e;
			}

			dos.writeUTF(resultado.getEquipoLocal());
			dos.writeChar('\t');
			dos.writeUTF(resultado.getEquipoVisitante());
			dos.writeChar('\t');
			dos.writeInt(resultado.getGolesLocal());
			dos.writeChar('\t');
			dos.writeInt(resultado.getGolesVisitante());
			dos.writeChar('\t');
			dos.writeUTF(resultado.getLugar());
			dos.writeChar('\t');
			dos.writeUTF(fechaStr);
			dos.writeChar('\n');

		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (null != dos)
					dos.close();
			} catch (IOException e) {
			}
			try {
				if (null != fos)
					fos.close();
			} catch (IOException e) {
			}
		}
	}
}
