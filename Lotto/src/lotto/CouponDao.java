package lotto;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface CouponDao {

	void addToCouponDatabase(Coupon coupon);

	void printCoupons() throws FileNotFoundException, IOException;

	void lookForWinner(int[] result, Game game) throws FileNotFoundException, IOException;

	void printStatistics() throws FileNotFoundException, IOException;

	void clearDatabase();
}
