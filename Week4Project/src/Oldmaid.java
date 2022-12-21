import java.util.*;
import java.time.*;
import javax.swing.JOptionPane;

/**
 * This program helps the user play Old Maid, a simple card game 
 * that is described in the output statements at the beginning of 
 * the main() routine. One can repeat the game as desired.  All players
 * are computer players.
 */
public class Oldmaid {
   public static void main(String[] args) {   
	   GameControl controller= new GameController();   
	   controller.runGame();   
   }  
} 

class IOHandler implements IGameView{
	//Scanner  sc = new Scanner(System.in);
	char input;
	private static char[] matches = new char[]{'f', 't'};

	@Override
	public void display(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	@Override
	public Character getInput(String msg) {
		boolean isCorrectInput = false;
		do {
            input = JOptionPane.showInputDialog(msg).charAt(0);
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

	@Override
	public void getResult(String prompt) {
		// TODO Auto-generated method stub		
	}	
}

class GameController extends GameControl{  //game model + game control
	Hand current = null;
	int currPlay = 0;
	public GameController(){
		super();
	}
	
	@Override
	public void init(){
		view.display("This program lets you play a card game: Crazy Eights\n");
		//players.add(new OMHumanPlayer("1"));
	    //for(int i = 1; i <= 3; i++){
	    	//players.add(new OldMaidPlayer(""+ (i+1)));
	    //}  
	}
	
	@Override
	public void startGame(){
		dealCards();
	}
	
	/**
	  * Play one round of Old Maid --  each player has played once
	 */
	 @Override
	 public void playRound() {	   
		 for(int i = 0; i < group.length; i++){
			 current = group[i];
			 currPlay = i + 1;
			 playTurn(i);
			 /*
			 if(current.isHandEmpty()) continue;
			 prior = getPriorPlayer(i);
			 if(prior == null) return;
			 if(current instanceof OMHumanPlayer){
				 current.play();	
				 continue;
			 }
			 Card c = prior.giveCard();
			 current.play(c);
			 */
		 }	     
	 } 
	 
	 @Override
	 public void endGame() {
		 if(isWinner() != -1) {
		   	view.display("Congratulations to Player " + isWinner() + " for Winning!");
	    }
	 }
	 
	 @Override
	 public int numOfPlayers() {
		 int player = Integer.parseInt(JOptionPane.showInputDialog("How many people will be playing (2-6)?"));
		 if(player < 2 && player > 6) {
			 System.out.println("I'm sorry, but the option that you chose is not available, please try again");
			 System.exit(0);
		 }
		 return player;
	 }
	 
	 @Override
	 public boolean isEmpty(){
		 if(extrasPile.isHandEmpty()) {
			 while( 1 < discardPile.getCardCount()) {
				extrasPile.addCard(discardPile.getCard(1));
				discardPile.removeCard(1);
				
			 }
			 view.display("The deck was restocked");
		 }
		return false;
	 }
	 
	 @Override
	 public int isWinner(){
		for(int i = 0; i < group.length; i++){
		   	if (group[i].isHandEmpty()){
		   		return (i + 1);
		    }
		} 
		return -1;
	 }
	 
	 private void grabNextCard(int num) {
		 group[num].addCard(extrasPile.getCard(0));
		 view.display("Player " + (currPlay) + " had to draw and drew the " + extrasPile.getCard(0));
		 extrasPile.removeCard(0);
	 }
	 
	 private boolean hasMatch() {
			return findMatch() != -1;//If -1 there is no match
	 }
		
	private int findMatch() {
		int holdEight = -1;
		boolean match = false;
		Card p;
		Card c = null;
		for(int i = 0; i < current.getCardCount(); i++) {
			p = discardPile.getCard(discardPile.getCardCount() - 1);
			c = current.getCard(i);
			
			if(c.getValue() == 8) holdEight = i;//Finds an 8, but doesn't use it right away
			if(p.getValue() == c.getValue()) {//Looking for other rank matches so doesn't quit right away
				discardPile.addCard(c);
				view.display("Player " + (currPlay) + " has played the " + c);
				current.removeCard(i);
				i--;
				match = true;
			}
			if(p.getSuit() == c.getSuit() && !match) {//Returns that it found a match
				discardPile.addCard(c);
				view.display("Player " + (currPlay) + " has played the " + c);
				current.removeCard(i);
				return 0;
			}
		}
		if(match) return 0;//If it found a match returns
		if(holdEight > -1) {//If it has to play an Eight it will
			discardPile.addCard(current.getCard(holdEight));
			view.display("Player " + (currPlay) + " has played the " + c);
			current.removeCard(holdEight);
			return 0;
		}
		return -1;
	}
	
	 private void playTurn(int i) {
		while(!hasMatch() && !isEmpty()) {
			grabNextCard(i);
		}
	 }
	 
	private void dealCards(){
		for(int i = 0; i < num * 7; i++) {//Giving the players their Cards
	    	  for(int j = 0; i < num * 7; j++) {
	    		  Card newCard = deck.dealCard();
	    		  group[j].addCard(newCard);
	    		  
	    		  if(j == (group.length - 1))
	    			  j=-1;
	    		  i++;
	    	  }
	      }
		
		Card newCard = deck.dealCard();
		discardPile.addCard(newCard);//Getting the starting pile Card
		
		for(int i = 0; i < (52 - (num * 7)) - 1; i++) {
			newCard = deck.dealCard();
			extrasPile.addCard(newCard);//Giving the grabbable Cards
		}
		
		while(discardPile.getCard(0).getValue() == 8) {//Incase the starting card is an 8
			Card temp = discardPile.getCard(0);
			discardPile.addCard(temp);
			discardPile.removeCard(0);
		}
	 }
}
