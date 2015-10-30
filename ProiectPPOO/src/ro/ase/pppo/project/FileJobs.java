package ro.ase.pppo.project;

import java.io.*;

public class FileJobs {
	
	public void createFile(String name, String path){
		
		File f = new File(name+path+".txt");
		
		try {
			if(f.createNewFile())
				System.out.println("New file created: " + f.getPath());
			else
				System.out.println("File already exists");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
