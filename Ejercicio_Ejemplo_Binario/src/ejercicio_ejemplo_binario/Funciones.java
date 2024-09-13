package ejercicio_ejemplo_binario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// 1eac93cd02a9f7c40f733c4bcd50ee7a
public class Funciones {
	protected void leerFichero(String ruta) {
		try {
			
			File fichero = new File(ruta);
			FileInputStream fic = new FileInputStream(fichero);

			// i decimal, pasamos a hex
			int i;
			while ((i = fic.read()) != -1) {
				System.out.println(Integer.toHexString(i) + ": " + (char) i);
			}
			
			fic.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected String devolverPassMD5deFichero(String ruta) {
		try {
			
			File fichero = new File(ruta);
			FileInputStream fic = new FileInputStream(fichero);
			
			String passMD5 = "";
			
			int i;
			while ((i = fic.read()) != -1) {
				passMD5 += (char) i;
			}
			
			fic.close();
			
			return passMD5;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected void escribirFichero(String ruta, String contenido, boolean append) {
		try {
			File fichero = new File(ruta);
			FileOutputStream foc = new FileOutputStream(fichero, append);
			
			char[] charsContenido = contenido.toCharArray();
			
			for (char charContenido : charsContenido) {
				foc.write(charContenido);
			}
			
			String lineSeparator = System.getProperty("line.separator");
			foc.write(lineSeparator.getBytes());
			
			foc.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected String encriptarMD5(String pass) {
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(pass.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1,digest);
			String hashtext = bigInt.toString(16);
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
