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
import java.util.Arrays;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CouponDaoImpl implements CouponDao {

	Path couponDatabase = Paths.get("C:/Output/databaseVariant1.csv");
	private static CouponDaoImpl couponDaoImpl = new CouponDaoImpl();

	private CouponDaoImpl() {
	}

	public static CouponDaoImpl getInstance() {
		return couponDaoImpl;
	}

	@Override
	public void clearDatabase() {
		List<String> lines = Arrays.asList("Bet number, Game name, Numbers, Coupon id, Client id");
		try {
			Files.write(couponDatabase, lines, Charset.forName("UTF-8"), StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addCouponsToDatabase(List<String> bets) throws IOException {		
			FileWriter writer = new FileWriter(couponDatabase.toString(), true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			System.out.print("Writing buffered (buffer size: " + ")... ");
			write(bets, bufferedWriter);		
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
		CSVParser parser = new CSVParser(new FileReader(couponDatabase.toString()), CSVFormat.DEFAULT.withSkipHeaderRecord(true)
				.withHeader("Bet number", "Game name", "Numbers", "Coupon id", "Client id").withDelimiter('|'));
		for (CSVRecord record : parser) {
			System.out.println(record.get("Bet number") + " " + record.get("Game name") + " " + record.get("Numbers")
					+ " " + record.get("Coupon id") + " " + record.get("Client id"));
		}
		parser.close();
	}
}
