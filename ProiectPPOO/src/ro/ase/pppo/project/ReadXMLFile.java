package ro.ase.pppo.project;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ReadXMLFile{
	public static void readXwriteF() {
		
		FileModerator.writeToFile("Attributes", "Values");
		double totalValue = 0;
		try {
			//TODO
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//			Document doc = dBuilder.parse(new File(accessProp().getProperty("XML")));
			Document doc = dBuilder.parse(new File(Props.getInstance().getProperty("XML")));

			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("product");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
//				System.out.println("\nCurrent Element :" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					FileModerator.writeToFile("Product Id : ", eElement.getAttribute("id"));
					FileModerator.writeToFile("Product Name : ", eElement.getElementsByTagName("productname").item(0).getTextContent());
					FileModerator.writeToFile("Product Quantity : ", eElement.getElementsByTagName("quantity").item(0).getTextContent());
					FileModerator.writeToFile("Product Price : ", eElement.getElementsByTagName("price").item(0).getTextContent());
					FileModerator.writeToFile("Prouct Currency : ", eElement.getElementsByTagName("currency").item(0).getTextContent());
					FileModerator.writeToFile("Product Category : ", eElement.getElementsByTagName("category").item(0).getTextContent());
					
					String quantity = eElement.getElementsByTagName("quantity").item(0).getTextContent();
					String price = eElement.getElementsByTagName("price").item(0).getTextContent();
					String currency = eElement.getElementsByTagName("currency").item(0).getTextContent();
					
					double value = Double.parseDouble(quantity) * Double.parseDouble(price);
					if(currency.equalsIgnoreCase("ron"))
						totalValue+=value;
					else{
						value = value * ExchangeEuroRate.getCurencyExchangeRate();
						totalValue+=value;
					} 
					FileModerator.writeToFile("**Product Value", String.valueOf(value)+currency);
					FileModerator.writeToFile(" ", " ");
				}
			}
			FileModerator.writeToFile(" ", " ");
			FileModerator.writeToFile(" ", " ");
			FileModerator.writeToFile("Total Bill Value", String.valueOf(totalValue)+"RON");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
