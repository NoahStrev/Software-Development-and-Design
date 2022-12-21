public interface IGameControl {
	void playRound();
	void runGame();
	void init();
}

interface IGameView{
	void display(String message);
	int getPlayers(String prompt);
	int grabCard(int lowerbound, int upperbound);
}
