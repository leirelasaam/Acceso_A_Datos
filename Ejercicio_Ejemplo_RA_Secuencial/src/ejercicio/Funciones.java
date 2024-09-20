package ejercicio;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Funciones {

	// Escribir 3 personas
	protected void ejercicio1() throws FileNotFoundException, EOFException, IOException {
		File fichero = null;
		RandomAccessFile raf = null;
		try {
			fichero = new File("src\\ejercicio\\fichero.dat");
			raf = new RandomAccessFile(fichero, "rw");

			ArrayList<Persona> personas = new ArrayList<Persona>();
			personas.add(new Persona("Lasa", 1, 10, 1000.45));
			personas.add(new Persona("Amo", 2, 20, 2400.60));
			personas.add(new Persona("Rebolledo", 3, 30, 3000.0));

			StringBuffer bf = null;

			for (Persona persona : personas) {
				raf.writeInt(persona.getId());
				bf = new StringBuffer(persona.getApellido());
				bf.setLength(10);
				raf.writeChars(bf.toString());
				raf.writeInt(persona.getDep());
				raf.writeDouble(persona.getSalario());

				System.out.println(persona.toString());
			}

			System.out.println("Escritura completada.");
		} catch (FileNotFoundException e) {
			throw e;
		} catch (EOFException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (null != raf)
					raf.close();
			} catch (IOException e) {
			}
		}
	}

	// Sobreescribir la posición 2
	/*
	 * No se puede insertar sin sobreescribir en la posición
	 * En caso de querer insertar, deberíamos:
	 * 		1. copiar la primera parte y añadirla en un nuevo fichero
	 * 		2. añadir nueva parte que queremos
	 * 		3. añadir la última parte
	 * 		4. eliminar el fichero antiguo y renombrar el nuevo
	 */
	protected void ejercicio2() throws FileNotFoundException, EOFException, IOException {
		File fichero = null;
		RandomAccessFile raf = null;
		try {
			fichero = new File("src\\ejercicio\\fichero.dat");
			raf = new RandomAccessFile(fichero, "rw");
			int id = 2;
			Persona persona = new Persona("Etxeberria", 2, 20, 2000.5);

			StringBuffer bf = null;
			long posicion = (id - 1) * 36;
			raf.seek(posicion);
			raf.writeInt(persona.getId());
			bf = new StringBuffer(persona.getApellido());
			bf.setLength(10);
			raf.writeChars(bf.toString());
			raf.writeInt(persona.getDep());
			raf.writeDouble(persona.getSalario());

			System.out.println("Escritura completada en la posición 2");
		} catch (FileNotFoundException e) {
			throw e;
		} catch (EOFException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (null != raf)
					raf.close();
			} catch (IOException e) {
			}
		}
	}
	
	protected void ejercicio3() throws FileNotFoundException, EOFException, IOException {
		File fichero = null;
		RandomAccessFile raf = null;
		ArrayList<Persona> personas = null;

		try {
			fichero = new File("src\\ejercicio\\fichero.dat");
			raf = new RandomAccessFile(fichero, "rw");
			
			int id, dep, posicion;
			double salario;
			char apellido[] = new char[10];
			posicion = 0;
			
			// Debe ponerse fuera
			raf.seek(posicion);
			
			// getFilePointer devuelve la posición en la que está el puntero
			// raf.length() devuelve el tamaño del fichero
			while(raf.getFilePointer() < raf.length()) {
				id = raf.readInt();
				
				for (int i = 0; i < apellido.length; i++) {
					apellido[i] = raf.readChar();
				}
				
				String ape = new String(apellido).trim();
				
				dep = raf.readInt();
				salario = raf.readDouble();
				
				if (null == personas)
					personas = new ArrayList<Persona>();
				personas.add(new Persona(ape, id, dep, salario));
			}

			System.out.println("Lectura completada.");
			System.out.println(personas.toString());
		} catch (FileNotFoundException e) {
			throw e;
		} catch (EOFException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (null != raf)
					raf.close();
			} catch (IOException e) {
			}
		}
	}
}
