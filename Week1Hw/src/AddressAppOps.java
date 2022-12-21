import java.util.*;
import javax.swing.*;

class AddressAppController implements AppController{
	AppView view;
	IAddressBook book;
	IDataHandler handler;
	String input, viewName, str;
	int job;
	LinkedQueue<String> reserve = new LinkedQueue<String>();
	LinkedQueue<String> walkup = new LinkedQueue<String>();
	
	AddressAppController(){
		appInit();
	}

	@Override
	public void setView(String name) {
		this.viewName = name; 
		if(viewName.equalsIgnoreCase("console"))
			view = new ConsoleAppView(book);
		else if(viewName.equalsIgnoreCase("gui"))
			view = new GUIAppView(book);
		else view = null;
		/*
		if(view != null){
			view.display( "Current names \n" + ((AddressBook) book).getKeyset().toString() );
		}
		else System.exit(1);
		*/
	}
	
	@Override
	public void appInit() {
		book = new AddressBook();
		handler = new DataHandler(book);
		handler.getData("addresses.txt");
	}

	@Override
	public void run() {
		do{
			job = view.getInt("Are you a customer(enter 1) or staffer(enter 2)?");
			if(job == 1){
				String fname = view.getInput(">Enter your first name");
				String lname = view.getInput(">Enter your last initial");
				boolean app = view.getApp(">Do you have an appointment(enter 1 for yes, 0 for no)");
				int reason = view.getReason(">Why are you here? Select one by entering the number:\n\t1. TV service\n\t2. Internet service\n\t3. Cell phone service\n\t4. Steam service\n\t5. Other");
				str = fname + " " + lname;
				if(app) {
					reserve.enqueue(str);
					reserve.enqueue(Integer.toString(reason));
				}
				else {
					walkup.enqueue(str);
					walkup.enqueue(Integer.toString(reason));
				}
				//book.add(fname, lname, app, reason);
				view.display(">You are set!");
				//view.display( ((AddressBook) book).getKeyset().toString()); 
			}
			else if(job == 2){
				if(reserve.isEmpty() == false) {
					view.display(">The front customer from the appointment line is");
					view.present(reserve);
					walkup.dequeue();
				}
				else if(walkup.isEmpty() == false) {
					view.display(">The front customer from the walk-in line is");
					view.present(walkup);
					walkup.dequeue();
				}
				else
					view.display("There is no one in line right now, check back later.");
				System.exit(0);
			}
		}while(true);		
	}
}

class ConsoleAppView implements AppView {
	private Scanner sc;
	String input;
	IAddressBook book;
	int job, reason, app;
	
	ConsoleAppView(IAddressBook b){
		book = b;
		sc = new Scanner(System.in);
	}
	
	@Override
	public String getInput(String prompt) {
		System.out.println(prompt);
		input = sc.next();
		//input = JOptionPane.showInputDialog(prompt);
		return input;
	}
	
	@Override
	public int getInt(String prompt) {
		System.out.println(prompt);
		job = sc.nextInt();
		if(job != 1 && job != 2) {
			System.out.println("I'm sorry, but the option that you chose is not available, please try again");
			System.exit(0);
		}
		//input = JOptionPane.showInputDialog(prompt);
		return job;
	}
	
	@Override
	public int getReason(String prompt) {
		System.out.println(prompt);
		reason = sc.nextInt();
		if(reason > 5 && reason < 1) {
			System.out.println("I'm sorry, but the option that you chose is not available, please try again");
			System.exit(0);
		}
		//input = JOptionPane.showInputDialog(prompt);
		return reason;
	}
	
	@Override
	public boolean getApp(String prompt) {
		System.out.println(prompt);
		app = sc.nextInt();
		
		if(app != 1 && app != 0) {
			System.out.println("I'm sorry, but the option that you chose is not available, please try again");
			System.exit(0);
		}
		else if(app == 1)
			return true;
		//input = JOptionPane.showInputDialog(prompt);
		return false;
	}

	@Override
	public String getResult() {
		String result = book.getAddress(input.trim());
		return result;
	}
	
	@Override
	public void display(String msg) {
		//JOptionPane.showMessageDialog(null, msg );
		System.out.println(msg);		
	}
	
	@Override
	public void present(LinkedQueue<String> linkedQueue) {
		//JOptionPane.showMessageDialog(null, msg );
		System.out.println(linkedQueue);		
	}
	
}

class GUIAppView implements AppView{
	String input;
	IAddressBook book;
	JTextArea area;
	
	GUIAppView(IAddressBook b){
		book = b;
		getFrame("diaplay frame").setVisible(true);
	}
	
	JFrame getFrame(String title){
		JFrame frame = new JFrame(title);
		area = new JTextArea();
		frame.add(area);
		frame.setSize(500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		return frame;
	}

	@Override
	public String getInput(String prompt) {
		input = JOptionPane.showInputDialog(prompt).trim();
		return input;
	}

	@Override
	public String getResult() {
		String result = book.getAddress(input.trim());
		return result;
	}

	@Override
	public void display(String msg) {
		area.append("\n" + msg);		
	}

	@Override
	public int getInt(String prompt) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getApp(String prompt) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getReason(String prompt) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void present(LinkedQueue<String> linkedQueue) {
		// TODO Auto-generated method stub
		
	}	
}
