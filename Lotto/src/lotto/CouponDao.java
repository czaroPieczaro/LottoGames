package lotto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface CouponDao {

	void addCouponsToDatabase(List<String> bets) throws IOException;

	void printCoupons() throws FileNotFoundException, IOException;

	void clearDatabase();
}
