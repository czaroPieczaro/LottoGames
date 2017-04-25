package lotto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

public interface CouponDao {

	void addCouponToDatabase(Coupon coupon, LocalDateTime clientId);

	void printCoupons() throws FileNotFoundException, IOException;

	void lookForWinner(int[] result, Game game) throws FileNotFoundException, IOException;

	void printStatistics() throws FileNotFoundException, IOException;

	void clearDatabase();
}
