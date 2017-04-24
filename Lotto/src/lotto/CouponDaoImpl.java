package lotto;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CouponDaoImpl implements CouponDao {

	private String databasePath = "C:/Output/database.csv";
	private static CouponDaoImpl couponDaoImpl = new CouponDaoImpl();

	private CouponDaoImpl() {
	}

	public static CouponDaoImpl getInstance() {
		return couponDaoImpl;
	}

	public void printCoupons() throws FileNotFoundException, IOException {
		CSVParser parser = new CSVParser(new FileReader(databasePath), CSVFormat.DEFAULT.withSkipHeaderRecord(true)
				.withHeader("Col1", "Col2", "Col3", "Col4").withDelimiter('|'));
		for (CSVRecord record : parser) {
			System.out.println(record.get("Col1") + " " + record.get("Col2") + " " + record.get("Col3") + " "
					+ record.get("Col4"));
		}
		parser.close();
	}

	@Override
	public void clearDatabase() {
		Path file = Paths.get(databasePath);
		List<String> lines = Arrays.asList("bet number,game name, numbers, coupon id");
		try {
			Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addToCouponDatabase(Coupon coupon) {
		for (Map.Entry<String, int[]> entry : coupon.getBets().entrySet()) {
			Path file = Paths.get(databasePath);
			List<String> lines = Arrays
					.asList(entry.getKey() + "|" + Arrays.toString(entry.getValue()) + "|" + coupon.getCouponId());
			try {
				Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.CREATE,
						StandardOpenOption.APPEND);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void lookForWinner(int[] result, Game game) throws FileNotFoundException, IOException {
		CSVParser parser = new CSVParser(new FileReader(databasePath), CSVFormat.DEFAULT.withSkipHeaderRecord(true)
				.withHeader("Col1", "Col2", "Col3", "Col4").withDelimiter('|'));
		for (CSVRecord record : parser) {
			if (record.get("Col2").equals(game.gameName())) {
				String str = record.get("Col3");
				int[] arr = Arrays.stream(str.substring(1, str.length() - 1).split(",")).map(String::trim)
						.mapToInt(Integer::parseInt).toArray();
				if (findCommon(arr, result) >= game.winningTreshold()) {
					System.out.println(Arrays.toString(arr));
					System.out.println("Congratulations! You've guessed " + findCommon(arr, result)
							+ " numbers correctly in " + game.gameName() + " on coupon " + record.get("Col4"));
				}
			}
		}
		parser.close();
	}

	@Override
	public void printStatistics() throws FileNotFoundException, IOException {
		int numberOfBigLottoBets = 0;
		int numberOfSmallLottoBets = 0;
		int numberOfMultiLottoBets = 0;
		CSVParser parser = new CSVParser(new FileReader(databasePath), CSVFormat.DEFAULT.withSkipHeaderRecord(true)
				.withHeader("Col1", "Col2", "Col3", "Col4").withDelimiter('|'));
		for (CSVRecord record : parser) {
			if (record.get("Col2").equals("Big Lotto")) {
				numberOfBigLottoBets++;
			} else if (record.get("Col2").equals("Small Lotto")) {
				numberOfSmallLottoBets++;
			} else if (record.get("Col2").equals("Multi Lotto")) {
				numberOfMultiLottoBets++;
			}
		}
		System.out.println("Number of Big Lotto games is: " + numberOfBigLottoBets);
		System.out.println("Number of Small Lotto games is: " + numberOfSmallLottoBets);
		System.out.println("Number of Multi Lotto games is: " + numberOfMultiLottoBets);
		parser.close();
	}

	public int findCommon(int[] ar1, int[] ar2) {
		int i = 0, j = 0;
		int counter = 0;
		while (i < ar1.length && j < ar2.length) {
			if (ar1[i] == ar2[j]) {
				counter++;
				i++;
				j++;
			} else if (ar1[i] < ar2[j]) {
				i++;
			} else {
				j++;
			}
		}
		return counter;
	}
}
