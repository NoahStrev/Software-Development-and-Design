import java.util.*;
class firstQuestion{

	private List<Mail> mails;

	// unimportant code omitted

	public void handleMails(){

		for (int i = 0; i < mails.size(); i++){
			
			mailType(mails.get(i));

		}

	}
	
	public void mailType(Mail m) {
		m.processMail(m);
	}

}

interface Mail{
	
	void processMail(Mail m);
}

class Regular implements Mail{

	@Override
	public void processMail(Mail m) {
		
	}
	
}

class Priority implements Mail{

	@Override
	public void processMail(Mail m) {
		
	}
	
}

class Express implements Mail{

	@Override
	public void processMail(Mail m) {
		
	}
	
}

class firstClass implements Mail{

	@Override
	public void processMail(Mail m) {
		
	}
	
}

