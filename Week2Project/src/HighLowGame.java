import java.util.*;
import java.time.*;

/**
 * This program helps the user play HighLow, a simple card game 
 * that is described in the output statements at the beginning of 
 * the main() routine. After the user plays several rounds, 
 * the user's average score is reported.
 */
public class HighLowGame {
   public static void main(String[] args) {   
	   GameController controller= new GameController();   
	   controller.runGame();   
   } 
} 

class IOHandler implements IGameView{
	Scanner  sc = new Scanner(System.in);
	char input;
	int players;
	int grabbed;

	@Override
	public void display(String message) {
		System.out.println(message);	
	}
	
	@Override
	public int grabCard(int lowerbound, int upperbound) {
		while(true) {
			System.out.println("\n>Which Card would you like to grab(" + lowerbound + "-" + upperbound + ")?");
			grabbed = sc.nextInt();
			if(grabbed < lowerbound || grabbed > upperbound) {
				System.out.println("The Card you tried to grab was outside the range, please try again");
			}
			else {
				return grabbed - 1;
			}
		}
		
	}
	
	@Override
	public int getPlayers(String prompt) {
		System.out.println(prompt);
		players = sc.nextInt();
		if(players < 2 && players > 12) {
			System.out.println("I'm sorry, but the option that you chose is not available, please try again");
			System.exit(0);
		}
		//input = JOptionPane.showInputDialog(prompt);
		return players;
	}
	
}

class GameController implements IGameControl{  //game model + game control
	IGameView h = new IOHandler();
	public Deck deck;
	
	
	public GameController(){
		init();
	}
	
	@Override
	public void init(){
		String str = "Welcome to Old Maid Simulator! ";
		h.display(str);
	    deck = new Deck();   
	    deck.shuffle(); 
	}
	
	@Override
	public void runGame(){		 
	    playRound(); 
	    
	}
	
	/**
	    * Lets the user play one game of HighLow, and returns the
	    * user's score in that game.  The score is the number of
	    * correct guesses that the user makes.
	    */
	 @Override
	 public void playRound() {	   
	      Card newCard;
	      int players, card, human = 0, nextplayer, sum = 0;
	      players = h.getPlayers("How many people will be playing today?");
	      Hand[] group = new Hand[players];
	      
	      for(int i = 0; i < group.length; i++) {
	    	 group[i] = new Hand(); //Instantiate
	      }
	      
	      for(int i = 0; i < deck.cardCt; i++) {
	    	  for(int j = 0; i < deck.cardCt; j++) {
	    		  newCard = deck.dealCard();
	    		  group[j].addCard(newCard);
	    		  
	    		  if(j == (group.length - 1))
	    			  j=-1;
	    		  i++;
	    	  }
	      }
	      
	      for(int i = 0; i < group.length; i++) {//The Initial hand
	    	  h.display("\n>Player" + (i+1) +"'s Initial Hand");
	    	  for(int j = 0; j < group[i].getCardCount(); j++) {
	    		  System.out.println(group[i].getCard(j));
	    	  }
	      }
	      
	      
	      for(int i = 0; i < group.length; i++) {//Sort the Cards and remove initial Pairs
	    	 h.display("\n>Player" + (i+1) +"'s Initial Pairs");
	    	 group[i].sortByValue();
	    	 group[i].removePairs(group[i]);
	      }
	      
	      
	      while(true) {
		      for(int i = 0; i < group.length; i++) {//The Initial hand
		    	  nextplayer = i+1;
		    	  
		    	  if(nextplayer >= group.length )
			    	  nextplayer = 0;
		    	  
		    	  while(group[nextplayer].getCardCount() == 0){
		    		  nextplayer++;
			    	  if(nextplayer >= group.length )
				    	  nextplayer = 0;
		    	  }
		    	  
		    	  while(group[i].getCardCount() == 0){
		    		  i++;
			    	  if(i >= group.length )
				    	  i = 0;
		    	  }
		    	  
		    	  h.display("\n>Player" + (i + 1) +"'s Turn");
			      
		    	  if(i == human) {
		    		  card = h.grabCard(1, group[nextplayer].getCardCount());
		    	  }
		    	  else {
		    		  Random r = new Random();
		    		  card = r.nextInt(group[nextplayer].getCardCount());
		    	  }
		    	  
		    	  h.display("\n>Player" + (i + 1) +" got the " + group[nextplayer].getCard(card));
		    	  group[i].addCard(group[nextplayer].getCard(card));
		    	  group[nextplayer].removeCard(card);
		    	  group[i].sortByValue();
		    	  group[i].removePairs(group[i]);
		    	  
		    	  int player = 0;
		    	  
		    	  for(int j = 0; j < group.length; j++) {
		    		  int num = group[j].getCardCount();
		    		  sum += num;
		    		  if(num > 0)
		    			  player = i + 1;

		    	  }
		    	  
	    		  if(sum == 1) {
	    			  h.display("Player" + player + " had the Old Maid, they lose!");
	    			  System.exit(1);
	    		  }
		    	  sum = 0;
		      }
	    	  
	      }
	      
	      
	 }  //end playRound()

}