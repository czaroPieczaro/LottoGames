import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.time.LocalDateTime;

public class Lotto {
	public int maximum;
	public int minimum;
	int[] resultsArray;
	public int resultsArrayLength;
	LocalDateTime dateTimeOfDraw;
	public String gameName;
	public String record;
	public String id;

	public static class DuzyLotek extends Lotto {
		public DuzyLotek() {
			gameName = "Duzy Lotek";
			dateTimeOfDraw = LocalDateTime.now();
			maximum = 49;
			minimum = 1;
			resultsArrayLength = 6;
			resultsArray = new int[resultsArrayLength];
			id = gameName + " " + dateTimeOfDraw;
			DuzyLotekStorage storage = new DuzyLotekStorage(gameName, dateTimeOfDraw, id);
		}
	}

	public static class DuzyLotekStorage {
		String gameName;
		LocalDateTime dateTimeOfDraw;
		public String id;
		int[] resultsArray;
		
		public DuzyLotekStorage(String gameName, LocalDateTime dateTimeOfDraw, String id) {
			this.gameName = gameName;
			this.dateTimeOfDraw = dateTimeOfDraw;
			this.id = id;
			this.resultsArray = resultsArray;
			System.out.println("Storage message:" + gameName + dateTimeOfDraw + id);
		}
	}

	public void draw() {
		System.out.println(gameName + System.lineSeparator() + "Winning numbers are:");
		for (int i = minimum; i <= resultsArrayLength; i++) {
			boolean contains = true;

			while (contains == true) {
				int result = ThreadLocalRandom.current().nextInt(minimum, maximum);
				contains = IntStream.of(resultsArray).anyMatch(x -> x == result);

				if (contains == false) {
					this.resultsArray[i - 1] = result;
					System.out.println(result);
				}
			}
		}
	}

	public int[] getResult() {
		return resultsArray;
	}

	public LocalDateTime getTime() {
		return dateTimeOfDraw;
	}

	public String createId() {
		// id = "dsad";
		System.out.println("Id to: " + id);
		return id;
	}

	public void store() {
		System.out.println("STORAGE:" + getTime());
	}

	public static void main(String[] args) {
		DuzyLotek duzyLotekDraw = new DuzyLotek();
		System.out.println(duzyLotekDraw.getTime());
		duzyLotekDraw.draw();
		duzyLotekDraw.createId();
	}
}