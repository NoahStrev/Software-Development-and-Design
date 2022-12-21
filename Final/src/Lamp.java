public interface Lamp{
	
	public void turnOn();
	
	public void turnOff();
}

class Switch implements Lamp{
	protected boolean status;
	
	Switch(){
	}

	@Override
	public void turnOn() {
		status = true;
	}

	@Override
	public void turnOff() {
		status = false;
	}
	
	public String toString() {
		String str = "The status of the light bulb is ";
		
		if(status) {
			str += "on";
		}
		else {
			str += "off";
		}
		
		return str;
		
	}
}

class tester{
	public static void main(String[]args) {
		Lamp flicker = new Switch();
	}
}