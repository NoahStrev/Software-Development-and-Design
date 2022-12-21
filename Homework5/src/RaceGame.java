import java.util.*;
import java.time.*;
import javax.swing.JOptionPane;

public class RaceGame {
   public static void main(String[] args) {//Functional Cohesion- All parts inside are essential to the output
	   GameControl controller= new GameController();   
	   controller.runGame();   
   }  
} 

class IOHandler implements IGameView{
	Scanner  sc = new Scanner(System.in);
	char input;
	private static char[] matches = new char[]{'f', 't'};

	@Override
	public void display(String message) {//Functional Cohesion- All parts inside are essential to the output
		System.out.print(message);
	}

	@Override
	public Character getInput(String msg) {
		boolean isCorrectInput = false;
		do {
			System.out.print(msg);
			input = sc.next().charAt(0);
            input = Character.toLowerCase(input);
            for(int i = 0; i < matches.length; i++){
            	if (input == matches[i]) {   
            		return new Character(input);
            	}            	
            }
            System.out.print("Please respond with an expected character:  ");            
        } while (!isCorrectInput);    
		return null;
	}	
}

class GameController extends GameControl{  //game model + game control
	Racer current = null;
	int currPlay = 0;
	private Scanner sc;
	public GameController(){//Temporal Cohesion- Initializing variables
		super();
		sc = new Scanner(System.in);
	}
	
	@Override
	public void init(){//Logical-Action performed by calling input
		view.display("This program lets you play a Racing Game\n");
	}
	
	@Override
	public void startGame(){//Procedural Cohesion - The elements are grouped together to complete a task
		view.display("\n\tThe Track Length is " + winAmount);
		view.display("\n\t\tPlayer\t");
		for(int i = 0; i < positions.length; i++) {
			view.display((i+1) + "\t");
			positions[i].add(0);
			moveType[i] = moveType();
			timesLeft[i] = duration();
		}
		view.display("\n");
	}
	
	 @Override
	 public void playRound() {//Procedural Cohesion - The elements are grouped together to complete a task   
		 for(int i = 0; i < positions.length; i++){
			 currPlay = i;
			 int newPos = 0;
			 if(timesLeft[i] == 0) {
				moveType[i] = moveType();
				timesLeft[i] = duration();
			 }
			 else {
				 timesLeft[i]--;
			 }
			 
			 newPos = (moves(moveType[i]) + positions[i].getTop());
			 
			 if(newPos < 0) {
				 positions[i].add(0);
			 }
			 else {
				 positions[i].add(newPos);
			 }
		 }	     
	 } 
	 
	 @Override
	 public void endGame() {//Procedural Cohesion - The elements are grouped together to complete a task
		 if(isWinner() != -1) {
		   	view.display("\nCongratulations to Player " + isWinner() + " for Winning!");
	    }
	 }
	 
	 @Override
	 public int numOfPlayers() {//Functional Cohesion- All parts inside are essential to the output
		 view.display("How many people will be playing (2-6)?");
		 int player = sc.nextInt();
		 if(player < 2 && player > 6) {
			 System.out.println("I'm sorry, but the option that you chose is not available, please try again");
			 System.exit(0);
		 }
		 return player;
	 }
	 
	 @Override
	 public int isWinner(){//Functional Cohesion- All parts inside are essential to the output
		for(int i = 0; i < positions.length; i++){
		   	if (positions[i].getTop() >= winAmount){
		   		return (i + 1);
		    }
		} 
		return -1;
	 }
	 
	 @Override
	 public int findLeader() {//Logical-Action performed by calling input
		 int max = Integer.MIN_VALUE;
		 for(int i = 0; i < positions.length; i++){
			 if(positions[i].getTop() > max)
				 max = positions[i].getTop();
		 }
		 return max;
	 }
	 
	 @Override
	 public int findLast() {//Logical-Action performed by calling input
		 int min = Integer.MAX_VALUE;
		 for(int i = 0; i < positions.length; i++){
			 if(positions[i].getTop() < min)
				 min = positions[i].getTop();
		 }
		 return min;
	 }
	 
	 @Override
	 public int moves(int number) {//Procedural Cohesion - The elements are grouped together to complete a task
		 int newPos = 0;
		 int dice = diceRoll();
		 switch (number){//Easily able to add new shapes
		 case 1:
			 newPos = dice + (findLeader() - positions[currPlay].getTop())/2;
			 if(dice == 1 || dice == 2) 
				 newPos = newPos - (2 * newPos);
			break;
		 case 2:
			 newPos = dice;
			 if((dice % 2) == 0)
				 newPos = 3 * newPos;
			break;
		 case 3:
			newPos = dice + (positions[currPlay].getTop() - findLast())/2;
			 if(dice >= 3 && dice <= 6) 
				 newPos = newPos - (2 * newPos);
			break;
		 }
		 return newPos;
	 }
}
