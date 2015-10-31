package ro.ase.pppo.project;

public class FileApp {

	public static void main(String[] args){
		
		FileJobs jB = new FileJobs();
		Props pS = new Props();
		try{
			jB.createFile(pS.accessProp().getProperty("filename"), pS.accessProp().getProperty("pathone"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		ReadXMLFile.readXwriteF();
		
		FileModerator.readFile();
		
		
	}

}
