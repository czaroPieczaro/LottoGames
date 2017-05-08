package lotto;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class StatisticsDaoImpl {
	Path couponDatabase = Paths.get("C:/Output/databaseVariant1.csv");
	Path statisticDatabase = Paths.get("C:/Output/statisticsVariant1.csv");
	private static StatisticsDaoImpl statisticsDaoImpl = new StatisticsDaoImpl();
	List<String> stats = new ArrayList<String>();

	private StatisticsDaoImpl() {
	}

	public static StatisticsDaoImpl getInstance() {
		return statisticsDaoImpl;
	}

	public void clearStatisticsDatabase() {
		List<String> lines = Arrays.asList("Game name, Common part size, Common part, Bet number, Coupon Id, Client id");
		try {
			Files.write(statisticDatabase, lines, Charset.forName("UTF-8"), StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printStatistics() throws FileNotFoundException, IOException {
		CSVParser parser = new CSVParser(new FileReader(statisticDatabase.toString()), CSVFormat.DEFAULT.withSkipHeaderRecord(true)
				.withHeader("Game name", "Common part size", "Numbers/number of occurances").withDelimiter('|'));
		for (CSVRecord record : parser) {
			System.out.println(record.get("Game name") + " " + record.get("Common part size") + " "
					+ record.get("Numbers/number of occurances"));
		}
		parser.close();
	}

	public void printBigWinners() throws FileNotFoundException, IOException {
		CSVParser parser = new CSVParser(new FileReader(statisticDatabase.toString()),
				CSVFormat.DEFAULT.withSkipHeaderRecord(true)
						.withHeader("Game name", "Common part size", "Numbers", "Bet number", "Coupon id", "Client id")
						.withDelimiter('|'));
		for (CSVRecord record : parser) {
			if (record.get("Game name").contains(" Big Win")) {
				System.out.println(record.get("Game name") + " " + record.get("Common part size") + " "
						+ record.get("Numbers") + " " + record.get("Bet number") + " " + record.get("Coupon id") + " "
						+ record.get("Client id"));
			}
		}
		parser.close();
	}

	public void writeToStatisticsBigLotto(int[] bigLottoResult) throws FileNotFoundException, IOException {
		CSVParser parser = new CSVParser(new FileReader(couponDatabase.toString()), CSVFormat.DEFAULT.withSkipHeaderRecord(true)
				.withHeader("Bet number", "Game name", "Numbers", "Coupon id", "Client id").withDelimiter('|'));
		int bigCounter3 = 0;
		int bigCounter4 = 0;
		int bigCounter5 = 0;
		int bigCounter6 = 0;
		int bigCounter = 0;
		for (CSVRecord record : parser) {
			int numberOfCommonElements;
			String str = record.get("Numbers");
			int[] arr = Arrays.stream(str.substring(1, str.length() - 1).split(",")).map(String::trim)
					.mapToInt(Integer::parseInt).toArray();
			if (record.get("Game name").equals(Game.BIG.gameName())) {
				bigCounter++;
				List<Integer> commonPart = findCommon(arr, bigLottoResult);
				numberOfCommonElements = commonPart.size();
				if(numberOfCommonElements == Game.BIG.numberOfNumbersToChoose()){
					stats.add(Game.BIG.gameName() + " Big Win" + "|" + numberOfCommonElements + "|" + commonPart + "|"
							+ record.get("Bet number") + "|" + record.get("Coupon id") + "|"
							+ record.get("Client id"));
				}
				else if (numberOfCommonElements >= Game.BIG.winningTreshold()) {
					stats.add(Game.BIG.gameName() + "|" + numberOfCommonElements + "|" + commonPart + "|"
							+ record.get("Bet number") + "|" + record.get("Coupon id") + "|"
							+ record.get("Client id"));
					if (numberOfCommonElements == 3) {
						bigCounter3++;
					} else if (numberOfCommonElements == 4) {
						bigCounter4++;
					} else if (numberOfCommonElements == 5) {
						bigCounter5++;
					} else if (numberOfCommonElements == 6) {
						bigCounter6++;
					}
				}
			}
		}
		parser.close();
		stats.add("BL" + "Total" + "|" + bigCounter + "|" + "|" + "|");
		stats.add("BL" + "|" + 3 + "|" + bigCounter3 + "|" + "|" + "|");
		stats.add("BL" + "|" + 4 + "|" + bigCounter4 + "|" + "|" + "|");
		stats.add("BL" + "|" + 5 + "|" + bigCounter5 + "|" + "|" + "|");
		stats.add("BL" + "|" + 6 + "|" + bigCounter6 + "|" + "|" + "|");
	}

	public void writeToStatisticsSmallLotto(int[] smallLottoResult) throws FileNotFoundException, IOException {
		CSVParser parser = new CSVParser(new FileReader(couponDatabase.toString()), CSVFormat.DEFAULT.withSkipHeaderRecord(true)
				.withHeader("Bet number", "Game name", "Numbers", "Coupon id", "Client id").withDelimiter('|'));
		int smallCounter5 = 0;
		int smallCounter = 0;
		for (CSVRecord record : parser) {
			int numberOfCommonElements;
			String str = record.get("Numbers");
			int[] arr = Arrays.stream(str.substring(1, str.length() - 1).split(",")).map(String::trim)
					.mapToInt(Integer::parseInt).toArray();
			if (record.get("Game name").equals(Game.SMALL.gameName())) {
				smallCounter++;
				List<Integer> commonPart = findCommon(arr, smallLottoResult);
				numberOfCommonElements = commonPart.size();
				if (numberOfCommonElements >= Game.SMALL.winningTreshold()) {
					smallCounter5++;
					stats.add(Game.SMALL.gameName() + " Big Win" + "|" + numberOfCommonElements + "|" + commonPart + "|"
							+ record.get("Bet number") + "|" + record.get("Coupon id") + "|" + record.get("Client id"));
				}
			}
		}
		parser.close();
		stats.add("SL" + "Total" + "|" + smallCounter + "|" + "|" + "|");
		stats.add("SL" + "|" + 5 + "|" + smallCounter5 + "|" + "|" + "|");
	}

	public void writeToStatisticsMultiLotto(int[] multiLottoResult) throws FileNotFoundException, IOException {
		CSVParser parser = new CSVParser(new FileReader(couponDatabase.toString()), CSVFormat.DEFAULT.withSkipHeaderRecord(true)
				.withHeader("Bet number", "Game name", "Numbers", "Coupon id", "Client id").withDelimiter('|'));
		int multiCounter10 = 0;
		int multiCounter = 0;
		for (CSVRecord record : parser) {
			int numberOfCommonElements;
			String str = record.get("Numbers");
			int[] arr = Arrays.stream(str.substring(1, str.length() - 1).split(",")).map(String::trim)
					.mapToInt(Integer::parseInt).toArray();
			if (record.get("Game name").equals(Game.MULTI.gameName())) {
				multiCounter++;
				List<Integer> commonPart = findCommon(arr, multiLottoResult);
				numberOfCommonElements = commonPart.size();
				if (numberOfCommonElements >= Game.MULTI.winningTreshold()) {
					multiCounter10++;
					stats.add(Game.MULTI.gameName() + " Big Win" + "|" + numberOfCommonElements + "|" + commonPart + "|"
							+ record.get("Bet number") + "|" + record.get("Coupon id") + "|" + record.get("Client id"));
				}
			}
		}
		parser.close();
		stats.add("ML" + "Total" + "|" + multiCounter + "|" + "|" + "|");
		stats.add("ML" + "|" + 10 + "|" + multiCounter10 + "|" + "|" + "|");
	}

	List<String> getStats() {
		return stats;
	}

	public void addStatistics(List<String> stats) throws IOException {
		try {
			FileWriter writer = new FileWriter(statisticDatabase.toString(), true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			System.out.print("Writing buffered (buffer size: " + ")... ");
			write(stats, bufferedWriter);
		} finally {
		}
	}

	private void write(List<String> records, Writer writer) throws IOException {
		long start = System.currentTimeMillis();
		for (String record : records) {
			writer.write(record + "\n");
		}
		writer.flush();
		writer.close();
		long end = System.currentTimeMillis();
		System.out.println((end - start) / 1000f + " seconds");
	}

	public List<Integer> findCommon(int[] ar1, int[] ar2) {
		int i = 0, j = 0;
		List<Integer> commonPart = new ArrayList<Integer>();
		while (i < ar1.length && j < ar2.length) {
			if (ar1[i] == ar2[j]) {
				commonPart.add(ar1[i]);
				i++;
				j++;
			} else if (ar1[i] < ar2[j]) {
				i++;
			} else {
				j++;
			}
		}
		return commonPart;
	}
}
