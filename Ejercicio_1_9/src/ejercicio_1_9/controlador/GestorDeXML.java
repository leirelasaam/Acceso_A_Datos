package ejercicio_1_9.controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ejercicio_1_9.modelo.CD;

/**
 * Gestiona los ficheros XML.
 */
public class GestorDeXML {
	
	private static final String FICHERO_ORIGINAL = "src//ejercicio_1_9//cds.xml";
	private static final String FICHERO_NUEVO_CD = "src//ejercicio_1_9//cds_nuevo.xml";
	private static final String FICHERO_NUMBER = "src//ejercicio_1_9//cds_number.xml";
	
	/**
	 * Lee el fichero XML y devuelve un ArrayList con CDs.
	 * @return ArrayList de CDs
	 * @throws FileNotFoundException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public ArrayList<CD> leer() throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
		ArrayList<CD> cds = null;
		File archivo = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		
		try {
			archivo = new File(FICHERO_ORIGINAL);
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(archivo);
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("CD");
			for (int i = 0; i < nList.getLength(); i ++) {
				Node nNode = nList.item(i);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					// Recoger los valores del nodo
	                String title = eElement.getElementsByTagName("TITLE").item(0).getTextContent();
	                String artist = eElement.getElementsByTagName("ARTIST").item(0).getTextContent();
	                String country = eElement.getElementsByTagName("COUNTRY").item(0).getTextContent();
	                String company = eElement.getElementsByTagName("COMPANY").item(0).getTextContent();
	                String price = eElement.getElementsByTagName("PRICE").item(0).getTextContent();
	                String year = eElement.getElementsByTagName("YEAR").item(0).getTextContent();

	                // Crear un objeto CD y añadirlo a la lista
	                CD cd = new CD(title, artist, country, company, price, year);
	                
	                if(cds == null)
	                	cds = new ArrayList<CD>();
	                cds.add(cd);
				}
			}
			
		} catch (FileNotFoundException e) {
			throw e;
		} catch (ParserConfigurationException e) {
			throw e;
		} catch (SAXException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
		return cds; 
	}
	
	/**
	 * Añade la información de un nuevo CD al archivo XML, generando un nuevo archivo.
	 * 
	 * @param cd Objeto con la información a añadir
	 * @throws FileNotFoundException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws TransformerConfigurationException
	 * @throws TransformerException
	 */
	public void escribir(CD cd) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {
		File archivo = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		
		// Crear un mapa para emparejar elementos y valores
	    Map<String, String> elementos = new HashMap<>();
	    elementos.put("TITLE", cd.getTitulo());
	    elementos.put("ARTIST", cd.getArtista());
	    elementos.put("COUNTRY", cd.getPais());
	    elementos.put("COMPANY", cd.getSello());
	    elementos.put("PRICE", String.valueOf(cd.getPrecio()));
	    elementos.put("YEAR", String.valueOf(cd.getAnio()));
		
		try {
			archivo = new File(FICHERO_ORIGINAL);
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(archivo);
			doc.getDocumentElement().normalize();
			
			Element eCD = doc.createElement("CD");
			
			// Recorrer el mapa para crear los elementos y añadir los valores
			for (Map.Entry<String, String> elemento : elementos.entrySet()) {
				// Crear el elemento
	            Element e = doc.createElement(elemento.getKey());
	            // Añadir el valor
	            e.appendChild(doc.createTextNode(elemento.getValue()));
	            // Append del elemento a CD
	            eCD.appendChild(e);
	        }
			
			// Raíz
			Element root = doc.getDocumentElement();
			// Append del nuevo CD
			root.appendChild(eCD);
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer tr = tf.newTransformer();
			DOMSource source = new DOMSource(doc);
			// No sobreescribir el archivo, sino tenerlo en uno nuevo
			StreamResult result = new StreamResult(new File(FICHERO_NUEVO_CD));
			tr.transform(source, result);
			
		} catch (FileNotFoundException e) {
			throw e;
		} catch (ParserConfigurationException e) {
			throw e;
		} catch (SAXException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (TransformerConfigurationException e) {
			throw e;
		} catch (TransformerException e) {
			throw e;
		}
		
	}
	
	/**
	 * Añade un nodo NUMBER dentro de cada CD incluyendo su valor correspondiente.
	 * 
	 * @throws FileNotFoundException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws TransformerConfigurationException
	 * @throws TransformerException
	 */
	public void asignarNumero() throws FileNotFoundException, ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {
		File archivo = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
	   
		
		try {
			archivo = new File(FICHERO_ORIGINAL);
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(archivo);
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("CD");
			for (int i = 0; i < nList.getLength(); i ++) {
				// Obtener el elemento CD actual
				Element cdElement = (Element) nList.item(i);
	            // Crear el nuevo elemento NUMBER
	            Element numberElement = doc.createElement("NUMBER");
	            // Añadir el valor correspondiente
	            numberElement.appendChild(doc.createTextNode(String.valueOf(i + 1)));
	            // Añadir el elemento NUMBER al elemento CD
	            cdElement.appendChild(numberElement);
			}
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer tr = tf.newTransformer();
			DOMSource source = new DOMSource(doc);
			// No sobreescribir el archivo, sino tenerlo en uno nuevo
			StreamResult result = new StreamResult(new File(FICHERO_NUMBER));
			tr.transform(source, result);
			
		} catch (FileNotFoundException e) {
			throw e;
		} catch (ParserConfigurationException e) {
			throw e;
		} catch (SAXException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (TransformerConfigurationException e) {
			throw e;
		} catch (TransformerException e) {
			throw e;
		}
	}

}
