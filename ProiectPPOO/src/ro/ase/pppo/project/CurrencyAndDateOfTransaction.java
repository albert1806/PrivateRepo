package ro.ase.pppo.project;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CurrencyAndDateOfTransaction {

	public static double getCurencyExchangeRate(String currency){
		try {
			URL url = new URL("http://www.bnr.ro/nbrfxrates.xml");
			URLConnection connection = url.openConnection();

			Document doc = parseXML(connection.getInputStream());
			NodeList descNodes = doc.getElementsByTagName("Rate");
			for (int temp = 0; temp < descNodes.getLength(); temp++) {

				Node nNode = descNodes.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					if (eElement.getAttribute("currency").equals(currency)) {
						return Double.parseDouble(doc.getElementsByTagName("Rate").item(temp).getTextContent());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static String getCurrentDate(){
		try {
			URL url = new URL("http://www.bnr.ro/nbrfxrates.xml");
			URLConnection connection = url.openConnection();

			Document doc = parseXML(connection.getInputStream());
			NodeList nodeList= doc.getElementsByTagName("Cube");
			for (int temp = 0; temp < nodeList.getLength(); temp++) {
				Node nNode = nodeList.item(temp);
				Element eElement = (Element) nNode;
				String date = eElement.getAttribute("date");
				
				return date;
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	private static Document parseXML(InputStream stream) throws Exception {

		DocumentBuilderFactory objDocumentBuilderFactory = null;
		DocumentBuilder objDocumentBuilder = null;
		Document doc = null;
		try {
			objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
			objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

			doc = objDocumentBuilder.parse(stream);
		} catch (Exception ex) {
			throw ex;
		}

		return doc;
	}
}
