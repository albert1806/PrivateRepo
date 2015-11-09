package ro.ase.pppo.project;

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ReadStoreCommandsXml {
	
	private static void generateStoreBill() {
		FileModerator.writeToFile("Attributes", "Values");

		double totalValue = 0;
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(Props.getInstance().getProperty("XMLStore")));

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
					TrnDetails.generateItems(currency);
					
					if(!currency.equalsIgnoreCase(TrnDetails.getBaseCurrency())){
						double valueCurrency  = TrnDetails.getValueCurrency();
						
						StringBuilder sb = new StringBuilder();
						sb.append(String.valueOf(value))
							.append(" ").append(currency).append("(1 ").append(currency)
							.append(" = ").append(valueCurrency).append(" ")
							.append(TrnDetails.getBaseCurrency()).append(" )");
						FileModerator.writeToFile("**Product Value in Currency", sb.toString());
						value = value*valueCurrency;
						FileModerator.writeToFile("**Product Value in "+TrnDetails.getBaseCurrency(), String.valueOf(value)+" "+TrnDetails.getBaseCurrency());
						FileModerator.writeToFile(" ", " ");
						totalValue+=value;
					}
					else{			
						totalValue+=value;
						FileModerator.writeToFile("**Product Value", String.valueOf(value) +" "+currency);
						FileModerator.writeToFile(" ", " ");
					} 

				}
			}
			
			FileModerator.writeToFile("Current Date", TrnDetails.getDate());
			FileModerator.writeToFile("Base Currency", TrnDetails.getBaseCurrency());
			FileModerator.writeToFile(" ", " ");
			FileModerator.writeToFile(" ", " ");
			FileModerator.writeToFile("Total Bill Value", String.valueOf(totalValue)+" "+TrnDetails.getBaseCurrency());
			FileModerator.writeToFile("//", "//"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void generateBill(){
		generateStoreBill();
	}
}
