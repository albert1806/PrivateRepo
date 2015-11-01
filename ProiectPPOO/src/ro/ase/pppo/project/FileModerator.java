package ro.ase.pppo.project;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileModerator extends Props {

	public static void createFile() {

		try {
			File f = new File(accessProp().getProperty("createStore"));
			
			if (f.createNewFile())
				System.out.println("New file created: " + f.getPath());
			else
				System.out.println("File already exists");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void readFile() {
		try {

			List<String> lines = Files.readAllLines(Paths.get(accessProp().getProperty("createStore")),
					Charset.defaultCharset());
			for (String line : lines) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeToFile(String text1, String text2) {
		try {

			PrintWriter out = new PrintWriter(
					new BufferedWriter(new FileWriter(accessProp().getProperty("createStore"), true)));
			out.write(String.format("%20s %20s", text1, text2 + "\r\n"));
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
