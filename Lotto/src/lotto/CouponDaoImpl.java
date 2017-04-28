package lotto;

import java.io.BufferedWriter;
import java.io.File;
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
import java.util.Arrays;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CouponDaoImpl implements CouponDao {

	private final String databasePath = "C:/Output/databaseVariant1.csv";
	private File database = new File("C:/Output/databaseVariant1.csv");
	private static CouponDaoImpl couponDaoImpl = new CouponDaoImpl();

	private CouponDaoImpl() {
	}

	public static CouponDaoImpl getInstance() {
		return couponDaoImpl;
	}

	@Override
	public void clearDatabase() {
		Path file = Paths.get(databasePath);
		List<String> lines = Arrays.asList("Bet number, Game name, Numbers, Coupon id, Client id");
		try {
			Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addCouponsToDatabase(List<String> bets) throws IOException {
		try {
			FileWriter writer = new FileWriter(database, true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			System.out.print("Writing buffered (buffer size: " + ")... ");
			write(bets, bufferedWriter);
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

	@Override
	public void printCoupons() throws FileNotFoundException, IOException {
		CSVParser parser = new CSVParser(new FileReader(databasePath), CSVFormat.DEFAULT.withSkipHeaderRecord(true)
				.withHeader("Bet number", "Game name", "Numbers", "Coupon id", "Client id").withDelimiter('|'));
		for (CSVRecord record : parser) {
			System.out.println(record.get("Bet number") + " " + record.get("Game name") + " " + record.get("Numbers")
					+ " " + record.get("Coupon id") + " " + record.get("Client id"));
		}
		parser.close();
	}
}
