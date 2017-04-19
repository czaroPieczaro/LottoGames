package sub.randomizing.machines;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import lotto.Result;

public class SuperRandomizingMachine {
	int maximum;
	private int minimum = 1; // Must be changed if a game with different minimum
								// of range is introduced
	int numberOfDrawnNumbers; // resultsArrayLength
	String gameName;

	public Result draw() {
		int[] results = new int[this.numberOfDrawnNumbers];

		for (int i = 1; i <= numberOfDrawnNumbers; i++) {
			boolean contains = true;

			while (contains == true) {
				int numberDrawn = ThreadLocalRandom.current().nextInt(minimum, maximum + 1);
				contains = IntStream.of(results).anyMatch(x -> x == numberDrawn);

				if (contains == false) {
					results[i - 1] = numberDrawn;
				}
			}
		}
		Arrays.sort(results);
		Result resultInfo = new Result(gameName, results);
		return resultInfo;
	}

	public SuperRandomizingMachine() {

	}

	public static void printResult(Result resultInfo) {
		System.out.println(resultInfo.getId() + System.lineSeparator() + "Winning numbers are:");
		System.out.print(Arrays.toString(resultInfo.getResults()) + "\n" + "\n");
	}

	public int getMaximum() {
		return maximum;
	}

	public int getResultsArrayLength() {
		return numberOfDrawnNumbers;
	}

	public String getGameName() {
		return gameName;
	}

}