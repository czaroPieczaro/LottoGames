package lotto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class BettingShop {

	private List<Coupon> coupons = new ArrayList<Coupon>();
	private CouponDaoImpl couponDaoImpl = CouponDaoImpl.getInstance();
	private StatisticsDaoImpl statisticsDaoImpl = StatisticsDaoImpl.getInstance();

	public BettingShop() {

	}

	Coupon sellNewCoupon(int clientId) {
		Coupon coupon = new Coupon(clientId);
		return coupon;
	}

	public void addToCoupons(Coupon coupon) {
		coupons.add(coupon);
	}

	public void addCouponsToDatabase() throws IOException {
		List<String> allBets = new ArrayList<String>();
		for (Coupon coupon : coupons) {
			Map<Integer, Bet> bets = coupon.getBets();
			for (Map.Entry<Integer, Bet> entry : bets.entrySet()) {
				int betNumber = entry.getKey();
				String gameName = entry.getValue().getGame().gameName();
				int[] numbers = entry.getValue().getNumbers();
				LocalDateTime couponId = coupon.getCouponId();
				int clientId = coupon.getClientId();
				allBets.add(
						betNumber + "|" + gameName + "|" + Arrays.toString(numbers) + "|" + couponId + "|" + clientId);
			}
		}
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

	public void writeToStatistics(int[] result) throws FileNotFoundException, IOException {
		if (result.length == Game.BIG.numberOfNumbersToChoose()) {
			statisticsDaoImpl.writeToStatisticsBigLotto(result);
		} else if (result.length == Game.SMALL.numberOfNumbersToChoose()) {
			statisticsDaoImpl.writeToStatisticsSmallLotto(result);
		} else {
			statisticsDaoImpl.writeToStatisticsMultiLotto(result);
		}
	}

	public void addStatistics() throws IOException {
		statisticsDaoImpl.addStatistics(statisticsDaoImpl.getStats());
	}

	public void printStatistics() throws FileNotFoundException, IOException {
		statisticsDaoImpl.printStatistics();
	}

	public void printBigWinners() throws FileNotFoundException, IOException {
		statisticsDaoImpl.printBigWinners();
	}

}
