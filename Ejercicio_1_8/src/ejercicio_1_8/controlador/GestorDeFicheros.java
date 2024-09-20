package ejercicio_1_8.controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ejercicio_1_8.modelo.Resultado;

public class GestorDeFicheros {
	// Ruta relativa del fichero
	private static final String RUTA_RESULTADOS_TXT = "src\\ejercicio_1_8\\Resultados.dat";

	public GestorDeFicheros() {
		// Constructor vacío
	}

	public Resultado leer() throws FileNotFoundException, IOException, ParseException {
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
			fichero = new File(RUTA_RESULTADOS_TXT);
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
				}
			} catch (EOFException e) {
				// No hacer nada
			}

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate fechaDate = LocalDate.parse(fecha, formatter);

			resultado = new Resultado(eqLocal, eqVisitante, golesLocal, golesVisitante, lugar, fechaDate);

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

		return resultado;
	}
	
	public void escribir(Resultado resultado) throws FileNotFoundException, IOException, ParseException {
		File fichero = null;
		DataOutputStream dos = null;
		FileOutputStream fos = null;

		try {
			fichero = new File(RUTA_RESULTADOS_TXT);
			fos = new FileOutputStream(fichero);
			dos = new DataOutputStream(fos);
			
			LocalDate fecha = resultado.getFecha();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String fechaStr = fecha.format(formatter);
			
			dos.writeUTF(resultado.getEquipoLocal());
			dos.writeUTF(resultado.getEquipoVisitante());
			dos.writeInt(resultado.getGolesLocal());
			dos.writeInt(resultado.getGolesVisitante());
			dos.writeUTF(resultado.getLugar());
			dos.writeUTF(fechaStr);
			
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
