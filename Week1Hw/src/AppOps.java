interface AppView {
	String getInput(String prompt);
	String getResult();
	void display(String msg);
	int getInt(String prompt);
	boolean getApp(String prompt);
	int getReason(String prompt);
	void present(LinkedQueue<String> linkedQueue);
}

interface AppController{
	void appInit();
	void setView(String viewName);
	void run();
}

interface IAddressBook {
	void add(String name, String addresses);	
	int getSize();	
	void remove(String name); 	
	boolean contains(String name);	
	String getAddress(String name);
}

interface IDataHandler{
	void getData(String connectionStr);
	void saveData(String connectionStr);
}

