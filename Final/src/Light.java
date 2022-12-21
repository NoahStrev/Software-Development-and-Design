
public class Light {
	
	private boolean bulb;
	
	Light(){
		off();
	}
	
	public void on(){
		bulb = true;
	}
	
	public void off(){
		bulb = false;
	}
}

class Controller{
	protected commandReciever command;
	
	public void setCommand(commandReciever c) {
		command = c;
	}
	
	public void controllerExecuter() {
		command.execute();
	}
}

interface commandReciever{
	void execute();
}

class turnOn implements commandReciever{
	
	Light light;
	
	turnOn(){
		light = new Light();
	}
	
	@Override
	public void execute() {
		light.on();
	}
	
}

class turnOff implements commandReciever{
	
	Light light;
	
	turnOff(){
		light = new Light();
	}
	
	@Override
	public void execute() {
		light.off();
	}
	
}