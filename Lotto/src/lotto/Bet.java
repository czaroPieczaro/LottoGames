package lotto;

public class Bet {
	private Game game;
	private int[] numbers;

	public Bet(Game game) {
		this.game = game;
		numbers = new int[game.numberOfNumbersToChoose()];
	}

	public Bet() {
		game = Game.randomGame();
		numbers = new int[game.numberOfNumbersToChoose()];
	}

	public Game getGame() {
		return game;
	}

	public int[] getNumbers() {
		return numbers;
	}
}
