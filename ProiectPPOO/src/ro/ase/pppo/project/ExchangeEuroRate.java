package ro.ase.pppo.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExchangeEuroRate{
	
	public static double getCurencyExchangeRate() {

		double exchange = 1;

		BufferedReader br = null;
		
		try {
			String sCurrentLine;

			//br = new BufferedReader(new FileReader(accessProp().getProperty("currencyFile")));
			br = new BufferedReader(new FileReader(Props.getInstance().getProperty("currencyFile")));

			sCurrentLine = br.readLine();
			exchange = Double.parseDouble(sCurrentLine);

			return exchange;

		} catch (IOException e) {

			e.printStackTrace();
			return exchange;

		} finally {

			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}
	}
}
