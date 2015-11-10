package ro.ase.pppo.project;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileModerator {

	public static void createFile(boolean deletePreviousFile) {

		try {
			File f = new File(Props.getInstance().getProperty("createStore"));
			if (f.createNewFile())
				System.out.println("New file created: " + f.getPath());
			else{	
				System.out.println("File already exists, performing delete & create");
				f.delete();
				f.createNewFile();
				System.out.println("New file created: " + f.getPath());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void createFile() {

		try {
			File f = new File(Props.getInstance().getProperty("createStore"));
			if (f.createNewFile())
				System.out.println("New file created: " + f.getPath());
			else
				System.out.println("File already exists, performing delete & create");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void readFile() {
		try {
			List<String> lines = Files.readAllLines(Paths.get(Props.getInstance().getProperty("createStore")),Charset.defaultCharset());
			for (String line : lines) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeToFile(String text1, String text2) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(Props.getInstance().getProperty("createStore"), true)));
			out.write(String.format("%40s %40s", text1, text2 + "\r\n"));
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
