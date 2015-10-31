package ro.ase.pppo.project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Props {
	public static Properties accessProp() throws FileNotFoundException{
		
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();           
		InputStream inputStream = loader.getResourceAsStream("config.properties");
		 
		if (inputStream != null) {
			try {
				prop.load(inputStream);
				return prop;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			throw new FileNotFoundException("property file not found.");
		}
		return prop;
	}
}
