package ro.ase.pppo.project;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileModerator {
	
	public static void readFile(){
        try {
			Props pS = new Props();
			StringBuilder sb = new StringBuilder();
			
			sb.append(pS.accessProp().getProperty("pathone"))
					.append(pS.accessProp().getProperty("filename"))
					.append(pS.accessProp().getProperty("extensionone"));
			
            List<String> lines = Files.readAllLines(Paths.get(sb.toString()),Charset.defaultCharset());
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void writeToFile(String text1, String text2){
		try {
			
			Props pS = new Props();
			StringBuilder sb = new StringBuilder();
			
			sb.append(pS.accessProp().getProperty("pathone"))
					.append(pS.accessProp().getProperty("filename"))
					.append(pS.accessProp().getProperty("extensionone"));
			
		    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(sb.toString(), true)));
		    out.write(String.format("%20s %20s", text1, text2 + "\r\n"));
		    out.close();
		    
		} catch (Exception e) {
		   e.printStackTrace();
		}
	}
}
