package ro.ase.pppo.project;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class Props {
	// TODO nu cred ca e facut pentru multithread
	private final Properties configProp = new Properties();

	private Props() {
		InputStream in = this.getClass().getClassLoader()
				.getResourceAsStream("config.properties");
		try {
			configProp.load(in);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Properties file not found");
		}
	}

	private static class LazyHolder {
		private static final Props INSTANCE = new Props();
	}

	protected static Props getInstance() {
		return LazyHolder.INSTANCE;
	}

	public String getProperty(String key) {
		return configProp.getProperty(key);
	}
}