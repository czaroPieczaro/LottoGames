package lotto;

import java.util.Random;

public enum Game {
	BIG("Big Lotto", 49, 6, 3), SMALL("Small Lotto", 35, 5, 5), MULTI("Multi Lotto", 80, 20, 20);

	private final static int minimum = 1;
	private final String gameName;
	private final int maximum;
	private final int numberOfDrawnNumbers;
	private final int winningTreshold;

	private static final Game[] VALUES = values();
	private static final int SIZE = VALUES.length;
	private static final Random RANDOM = new Random();

	Game(String gameName, int maximum, int numberOfDrawnNumbers, int winningTreshold) {
		this.gameName = gameName;
		this.maximum = maximum;
		this.numberOfDrawnNumbers = numberOfDrawnNumbers;
		this.winningTreshold = winningTreshold;
	}

	String gameName() {
		return gameName;
	}

	public int maximum() {
		return maximum;
	}

	public int minimum() {
		return minimum;
	}

	public int numberOfDrawnNumbers() {
		return numberOfDrawnNumbers;
	}

	public int winningTreshold() {
		return winningTreshold;
	}

	public static Game randomGame() {
		return VALUES[RANDOM.nextInt(SIZE)];
	}

	public static Game gameGeneratedWithName(String gameName) {
		if (gameName.equals("Big Lotto")) {
			return BIG;
		} else if (gameName.equals("Small Lotto")) {
			return SMALL;
		} else {
			return MULTI;
		}
	}
}