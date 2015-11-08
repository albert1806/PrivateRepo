package ro.ase.pppo.project;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ReadStoreCommandsXml {
	public static void generateStoreBill() {
		//FileModerator.writeToFile("Current Date", CurrencyAndDateOfTransaction.getCurrentDate());
		CurrencyAndDateOfTransaction object2 = new CurrencyAndDateOfTransaction("");
		String newDate = object2.date;
		String baseCurrency = object2.baseCurrency;
		FileModerator.writeToFile("Current Date", newDate);
		FileModerator.writeToFile("Base Currency", baseCurrency);
		FileModerator.writeToFile("Attributes", "Values");
		double totalValue = 0;
		try {
			//TODO
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new File(Props.getInstance().getProperty("XMLStore")));
			
//			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("product");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
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
					if(currency.equalsIgnoreCase(baseCurrency)){
						totalValue+=value;
						FileModerator.writeToFile("**Product Value", String.valueOf(value) +" "+currency);
						FileModerator.writeToFile(" ", " ");
					}
					else{
						CurrencyAndDateOfTransaction object = new CurrencyAndDateOfTransaction(currency);
						double valueCurrency = object.valueCurrency;
						StringBuilder sb = new StringBuilder();
						sb.append(String.valueOf(value))
							.append(" ").append(currency).append("(1 ").append(currency)
							.append(" = ").append(valueCurrency).append(" ")
							.append(baseCurrency).append(" )");
						FileModerator.writeToFile("**Product Value in Currency", sb.toString());
						//value = value * CurrencyAndDateOfTransaction.getCurencyExchangeRate(currency); 
						value = value*valueCurrency;
						FileModerator.writeToFile("**Product Value in "+baseCurrency, String.valueOf(value)+" "+baseCurrency);
						FileModerator.writeToFile(" ", " ");
						totalValue+=value;
					} 

				}
			}
			FileModerator.writeToFile(" ", " ");
			FileModerator.writeToFile(" ", " ");
			FileModerator.writeToFile("Total Bill Value", String.valueOf(totalValue)+" "+baseCurrency);
			FileModerator.writeToFile("//", "//"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
