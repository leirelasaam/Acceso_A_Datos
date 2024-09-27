package xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class EjemploXML {

	public static void main(String[] args) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			Element elementoRaiz = doc.createElement("coches");
			doc.appendChild(elementoRaiz);
			
			Element cochesU = doc.createElement("cochesutilitarios");
			elementoRaiz.appendChild(cochesU);
			
			Attr attr = doc.createAttribute("compania");
			attr.setValue("Hyundai");
			cochesU.setAttributeNode(attr);
			
			Element nCoche = doc.createElement("nombrecoche");
			Attr attrType = doc.createAttribute("tipo");
			attrType.setValue("4x4");
			nCoche.setAttributeNode(attrType);
			nCoche.appendChild(doc.createTextNode("Tucson"));
			cochesU.appendChild(nCoche);
			
			Element nCoche1 = doc.createElement("nombrecoche");
			Attr attrType1 = doc.createAttribute("tipo");
			attrType1.setValue("normal");
			nCoche1.setAttributeNode(attrType1);
			nCoche1.appendChild(doc.createTextNode("Coupe"));
			cochesU.appendChild(nCoche1);
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer tr = tf.newTransformer();
			// Indentación
			tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("src\\xml\\coches.xml"));
			tr.transform(source, result);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
