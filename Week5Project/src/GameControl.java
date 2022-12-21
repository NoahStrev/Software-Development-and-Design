import java.util.ArrayList;
import java.util.List;

public abstract class GameControl {
	protected IGameView view = new IOHandler();
	protected Racer[] positions;
	protected int[] moveType;
	protected int[] timesLeft;
	protected int numPlayers;
	protected int winAmount;
	
	public GameControl(){//Temporal Cohesion- Initializing variables
		init();		
		numPlayers = 0;
		winAmount = 50;
	}
	public void runGame(){//Functional Cohesion- All parts inside are essential to the output
		Character input = view.getInput("Play? (t/f)" + "?");
		if( input != 't') return;  
	    do {
	    	numPlayers = numOfPlayers();
	    	positions = new Racer[numPlayers];
	    	moveType = new int[numPlayers];
	    	timesLeft = new int[numPlayers];
		    for(int i = 0; i < positions.length; i++) {
		    	positions[i] = new Racer(); //Instantiate
			}
			startGame();
	    	do{
	    		view.display(playersPosition());
	    		playRound(); 
	    	}while(isWinner() == -1);  
	    	view.display(playersPosition());
	    	endGame();
	    } while ( ((char) view.getInput("\nPlay again (t/f)" + "?")) == 't');	  
	}
	abstract void init();
	abstract int numOfPlayers();
	abstract void startGame();
	abstract void playRound();
	abstract void endGame();
	abstract int isWinner();
	abstract int findLeader();
	abstract int findLast();
	abstract int moves(int number);
	//Logical-Action performed by calling input
	public String playersPosition(){
		String str = "\nRound " + positions[0].getCount() + ": Positions ";
		for(int i = 0; i < numPlayers; i++){
			str +=  "\t" + positions[i].getTop();
		}
		return str;
	}
	//Logical-Action performed by calling input
	public int diceRoll() {//Rolling the dice
		int dice = ((int)(Math.random() * 6 + 1));
		return dice;
	}
	//Logical-Action performed by calling input
	public int moveType() {//Random number between 1-3
		int random = ((int)(Math.random() * 3 + 1));
		return random;
	}
	//Logical-Action performed by calling input
	public int duration() {//Random number between 1-5
		int random = ((int)(Math.random() * 5 + 1));
		return random;
	}
}

interface IGameView{//Functional-clear and defined method
	void display(String message);
	<T> T getInput(String msg);
}
