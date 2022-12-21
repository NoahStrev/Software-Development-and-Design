
public class Mover {
	int first, last, position, duration = 0, move = 0;
	
	public Mover() {
	}
	
	public void play(int f, int l, int p) {
		first = f;
		last = l;
		position = p;
		getMove();
		position += moves(move);
		isNegative();
	}
	
	public void getMove() {
		 if(duration == 0) {
			move = moveType();
			duration = duration();
		 }
		 else {
			duration--;
		 }
	}
	
	public int moves(int number) {
		int newPos = 0;
		int dice = diceRoll();
		switch (number){
		case 1:
			newPos = dice + (first - position)/2;
			if(dice == 1 || dice == 2) 
				newPos = newPos - (2 * newPos);
			break;
		case 2:
			newPos = dice;
			if((dice % 2) == 0)
				newPos = 3 * newPos;
			break;
		case 3:
			newPos = dice + (position - last)/2;
			if(dice >= 3 && dice <= 6) 
				newPos = newPos - (2 * newPos);
			break;
		}
		return newPos;
	}
	
	public int diceRoll() {
		int dice = ((int)(Math.random() * 6 + 1));
		return dice;
	}
	
	public int moveType() {
		int random = ((int)(Math.random() * 3 + 1));
		return random;
	}
	
	public int duration() {
		int random = ((int)(Math.random() * 5 + 1));
		return random;
	}
	
	public void isNegative() {
		if(position < 20)
			position = 20;
	}
}
