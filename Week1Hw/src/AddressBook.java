import java.util.*;

public class AddressBook implements IAddressBook{
	private HashMap<String, String> addresses;
	
	public AddressBook(){
		addresses = new HashMap<String, String>();
	}
	
	public void add(String name, String address){
		addresses.put(name.toLowerCase(), address);
	}
	
	public int getSize(){
		return addresses.size();
	}
	
	public void remove(String name){
		addresses.remove(name.toLowerCase());
	}
	
	public Set<String> getKeyset(){
		return addresses.keySet();
	}
	
	public boolean contains(String name){
		Set<String> names = getKeyset();
		return names.contains(name.toLowerCase());
	}
	
	public String getAddress(String name){
		String toReturn = "";
		if(contains(name)){
			toReturn = addresses.get(name);
		}
		return toReturn;
	}

	public String toString(){
		String toReturn = "";
		for (Map.Entry<String, String> entry : addresses.entrySet()) {
		    toReturn += entry.getKey() + " " + addresses.get(entry.getKey()) + "\n"; 
		}
		return toReturn;
	}
}

