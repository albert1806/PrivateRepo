package ro.ase.pppo.project;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileModerator {
	
	static String fullPath = FileApp.getPath()+FileApp.getName()+".txt";
	
	public static void readFile(){
        try {
            List<String> lines = Files.readAllLines(Paths.get(fullPath),Charset.defaultCharset());
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void writeToFile(String text){
		try {
		    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fullPath, true)));
		    out.println(text);
		    out.close();
		} catch (Exception e) {
		   e.printStackTrace();
		}
	}
}
