package packageLIREfichier;

// https://www.codeflow.site/fr/article/java__how-to-read-xml-file-in-java-dom-parser

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class lireXML {
	// Les attributs
	private String _valeur;

	// Constructeur
	public lireXML(String valeur) {
		this._valeur = valeur;
	}

	/**** Methode ****/
	// tag = session-config - tag2 = session-timeout
	public String retourDeValeurXML(String tag, String tag2, String path) {
		try {
			File fXmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			// optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
			System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName(tag);
			System.out.print("----------------------");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element : " + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					this._valeur = eElement.getElementsByTagName(tag2).item(0).getTextContent();
					System.out.println(tag2 + " : " + this._valeur);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this._valeur;
	}

	// Getters et Setters
	public String get_valeur() {
		return _valeur;
	}

	public void set_valeur(String _valeur) {
		this._valeur = _valeur;
	}
}
