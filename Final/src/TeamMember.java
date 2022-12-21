import java.util.*;

interface TeamMember {
	void perform();
}

class Leader implements TeamMember{
	private String name;
	
	Leader(String name){
		this.name = name;
	}
	
	@Override
	public void perform() {
		//perform an action
	}
	
}

class Member implements TeamMember{
	private String name;
	
	Member(String name){
		this.name = name;
	}
	
	@Override
	public void perform() {
		//perform an action
	}
	
}

class Group implements TeamMember{
	
	List<TeamMember> team;
	@Override
	public void perform() {
		//perform an action
	}
	
	public void addMember(TeamMember member) {
		team.add(member);
	}
	
	public void removeMember(TeamMember member) {
		team.remove(member);
	}
	
}