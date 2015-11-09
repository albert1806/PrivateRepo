package ro.ase.pppo.project;

import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class BnrCurrencyRate {
	private static final URLConnection urlConnection() {
		try {
			URL url = new URL("http://www.bnr.ro/nbrfxrates.xml");
			URLConnection connection = url.openConnection();
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Document exchangeRate() {
		try {
			DocumentBuilderFactory objDocumentBuilderFactory = null;
			DocumentBuilder objDocumentBuilder = null;
			Document doc = null;

			objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
			objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

			doc = objDocumentBuilder.parse(urlConnection().getInputStream());

			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected static Document getDocument() {
		return exchangeRate();
	}
}
