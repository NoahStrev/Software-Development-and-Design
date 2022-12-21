import java.io.*;
import java.util.*;

public class DataHandler implements IDataHandler{
	IAddressBook book;
	public DataHandler(IAddressBook book){
		this.book = book;
	}
	public void getData(String connStr){
        String name = null, address = null;
        String line = "";
        try {
            FileReader fileReader = new FileReader(connStr);            
            BufferedReader bufferedReader = new BufferedReader(fileReader);//Wrap FileReader in BufferedReader.

            while( (line = bufferedReader.readLine()) != null ) {
            	String[] parts = line.split(":", 2);
            	if(parts.length >= 2){
            		book.add(parts[0], parts[1]);            		
            	}             	
            } 
            bufferedReader.close();
        }
        catch(IOException ex) {
            System.out.println("Error in reading file"); 
            ex.printStackTrace();                  
        }
	}
	
	public void saveData(String connStr){
	    FileWriter fileWriter;  
	    BufferedWriter bufferedWriter; 
		try {
			fileWriter = new FileWriter(connStr);
			bufferedWriter = new BufferedWriter(fileWriter); 
			//Once writing objects are instantiated, the existing content of the file would be wiped out...
            Set<String> kset = ((AddressBook) book).getKeyset(); 
            Iterator<String> it = kset.iterator();
            while ( it.hasNext() ) {
            	String name = it.next();
            	String address = book.getAddress(name);
            	bufferedWriter.write(name + ":" + address);
            	bufferedWriter.write(System.getProperty ( "line.separator" ));
    		}
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error in writing to file");
            ex.printStackTrace();
        }
	}
}
