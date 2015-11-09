package ro.ase.pppo.project;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TrnDetails {
	private static String date;
	private static double valueCurrency;
	private static  String baseCurrency;
	
	 private static void readCurrencyXml(String currency) {
		try {
			
			NodeList nodeDate = BnrCurrencyRate.getDocument().getElementsByTagName("Cube");
			NodeList nodeCurrency = BnrCurrencyRate.getDocument().getElementsByTagName("OrigCurrency");
			NodeList nodeRates = BnrCurrencyRate.getDocument().getElementsByTagName("Rate");

			Node nNodeDate = nodeDate.item(0);
			Element eElementDate = (Element) nNodeDate;
			TrnDetails.date = eElementDate.getAttribute("date");
			
			Node nNodeCurrency = nodeCurrency.item(0);
			Element eElementCurrency = (Element) nNodeCurrency;
			TrnDetails.baseCurrency = eElementCurrency.getTextContent();
			
			if (!currency.isEmpty()) {

				for (int temp = 0; temp < nodeRates.getLength(); temp++) {

					Node nNode = nodeRates.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						if (eElement.getAttribute("currency").equals(currency)) {
							TrnDetails.valueCurrency = Double.parseDouble(BnrCurrencyRate.getDocument().getElementsByTagName("Rate").item(temp).getTextContent());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getDate() {
		return date;
	}

	public static double getValueCurrency() {
		return valueCurrency;
	}

	public static String getBaseCurrency() {
		return baseCurrency;
	}

	public static void generateItems(String currency) {
		readCurrencyXml(currency);
	}
}
