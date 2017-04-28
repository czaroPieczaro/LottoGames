package lotto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class BettingShop {
	private List<String> allBets = new ArrayList<String>();
	private CouponDaoImpl couponDaoImpl = CouponDaoImpl.getInstance();
	private StatisticsDaoImpl statisticsDaoImpl = StatisticsDaoImpl.getInstance();

	public BettingShop() {

	}

	public void addCouponsToDatabase() throws IOException {
		couponDaoImpl.addCouponsToDatabase(allBets);
	}

	public void printCouponsDao() throws FileNotFoundException, IOException {
		couponDaoImpl.printCoupons();
	}

	public void clearDatabase() {
		couponDaoImpl.clearDatabase();
	}

	public void clearStatisticsDatabase() {
		statisticsDaoImpl.clearStatisticsDatabase();
	}

	public void getStatistics(int[] bigLottoResult, int[] smallLottoResult, int[] multiLottoResult)
			throws FileNotFoundException, IOException {
		statisticsDaoImpl.getStatistics(bigLottoResult, smallLottoResult, multiLottoResult);
	}

	public void printStatistics() throws FileNotFoundException, IOException {
		statisticsDaoImpl.printStatistics();
	}

	public void printBigWinners() throws FileNotFoundException, IOException {
		statisticsDaoImpl.printBigWinners();
	}

	Coupon sellNewCoupon() {
		Coupon coupon = new Coupon();
		return coupon;
	}

	public void addToCoupons(Coupon coupon, LocalDateTime clientId) {
		Map<Integer, Bet> bets = coupon.getBets();
		for (Map.Entry<Integer, Bet> entry : bets.entrySet()) {
			int betNumber = entry.getKey();
			String gameName = entry.getValue().getGame().gameName();
			int[] numbers = entry.getValue().getNumbers();
			LocalDateTime couponId = coupon.getCouponId();
			allBets.add(betNumber + "|" + gameName + "|" + Arrays.toString(numbers) + "|" + couponId + "|" + clientId);
		}

	}
}
