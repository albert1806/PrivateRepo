package ro.ase.pppo.project;

public class FileApp {

	public static void main(String[] args) {

		FileModerator.createFile();

		ReadStoreCommandsXml.generateStoreBill();

		FileModerator.readFile();
		
	}

}
