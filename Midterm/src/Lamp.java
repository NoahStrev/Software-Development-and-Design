import java.util.*;

class Maximum{
	
	@SuppressWarnings("unchecked")
	public static ArrayList Comparision(ArrayList oldList, Comparator c){
		
		Collections.sort(oldList, c);
		ArrayList newList = new ArrayList();
		newList = (ArrayList) oldList.get(0);//Gets max or min
		newList = (ArrayList) oldList.get(oldList.size() - 1);//Gets other max or min
		
		return newList;
	}
}

public class Lamp{
	protected boolean status;
	
	Lamp(){
		status = false;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void turnOn(){
		status = true;
	}
	
	public void turnOff() {
		status = false;
	}
	

class Switch{
	protected Lamp lamp;
	Switch(){
		lamp = new Lamp();
	}
	
	public void changeStatus() {
		if(lamp.getStatus()) {
			lamp.turnOff();
		}
		else {
			lamp.turnOn();
		}
	}
	
	public String toString() {
		String str = "The status of the light bulb is ";
		
		if(lamp.getStatus()) {
			str += "on";
		}
		else {
			str += "off";
		}
		
		return str;
		
	}
}
