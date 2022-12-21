import java.util.ArrayList;
import java.util.List;

public abstract class GameControl {
	protected IGameView view = new IOHandler();
	protected Deck deck;
	protected List<Player> players;
	protected Hand[] group;
	protected Hand discardPile;
	protected Hand extrasPile;
	protected int num;
	
	public GameControl(){
		deck = new Deck();   
		deck.shuffle();	
		players = new ArrayList<Player>();
		init();		
		discardPile = new Hand();
		extrasPile = new Hand();
	}
	public void runGame(){
		int numRounds = 1;
		Character input = view.getInput("Play? (t/f)" + "?");
		if( input != 't') return;  
	    do {
	    	num = numOfPlayers();
	    	group = new Hand[num];
		    for(int i = 0; i < group.length; i++) {
		    	group[i] = new Hand(); //Instantiate
			}
			startGame();
	    	do{
	    		view.display("Round " + numRounds + ":");
	    		view.display("The Card on the top of the pile is " + discardPile.getCard(discardPile.getCardCount()-1));
	    		view.display(playersHands());
	    		playRound(); 
	    		numRounds++;
	    	}while(!isEmpty() && isWinner() == -1);   
	    	endGame();
	    } while ( ((char) view.getInput("Play again (t/f)" + "?")) == 't');	  
	}
	abstract void init();
	abstract int numOfPlayers();
	abstract void startGame();
	abstract void playRound();
	abstract void endGame();
	abstract boolean isEmpty();
	abstract int isWinner();
	
	public String playersHands(){
		String hands = "";
		for(int i = 0; i < num; i++){
			hands += "Player " + (i + 1) + " Hand size:" + group[i].getCardCount() + "\n" + group[i].displayHand(group[i]) + "\n\n";
		}
		return hands;
	}
}


interface IGameView{
	void getResult(String prompt);
	void display(String message);
	<T> T getInput(String msg);
}
