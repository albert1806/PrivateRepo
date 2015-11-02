package ro.ase.pppo.project;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class Props {
	//TODO nu cred ca e facut pentru multithread
	private final Properties configProp = new Properties();
	private Props(){
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("config.properties");
		System.out.println("Read all properties from file");
		
		try{
			configProp.load(in);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private static class LazyHolder{
		private static final Props INSTANCE = new Props();
	}
	
	protected static Props getInstance(){
		return LazyHolder.INSTANCE;
	}
	
	public String getProperty(String key){
		return configProp.getProperty(key);
	}
	
	public Set<String> getAllPropertyNames(){
		return configProp.stringPropertyNames();
	}
	
	public boolean containsKey (String key){
		return configProp.containsKey(key);
	}
	
	//TODO Singleton to read from props
//	public static Properties accessProp() throws FileNotFoundException{
//		
//		Properties prop = new Properties();
//		ClassLoader loader = Thread.currentThread().getContextClassLoader();           
//		InputStream inputStream = loader.getResourceAsStream("config.properties");
//		 
//		if (inputStream != null) {
//			try {
//				prop.load(inputStream);
//				return prop;
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} else {
//			throw new FileNotFoundException("property file not found.");
//		}
//		return prop;
//	}
}
