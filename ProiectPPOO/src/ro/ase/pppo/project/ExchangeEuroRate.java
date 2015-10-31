package ro.ase.pppo.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExchangeEuroRate {
	public static double getCurencyExchangeRate() {

		double exchange = 1;

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			Props pS = new Props();
			sb.append(pS.accessProp().getProperty("pathtwo"))
					.append(pS.accessProp().getProperty("currency"))
					.append(pS.accessProp().getProperty("extensionone"));

			String sCurrentLine;

			br = new BufferedReader(new FileReader(sb.toString()));

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
