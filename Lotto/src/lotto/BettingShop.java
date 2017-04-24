package lotto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class BettingShop {
	private List<Coupon> coupons = new ArrayList<Coupon>();
	private CouponDaoImpl couponDaoImpl = CouponDaoImpl.getInstance();

	public BettingShop() {

	}

	public void addToCouponDatabase(Coupon coupon) {
		couponDaoImpl.addToCouponDatabase(coupon);
	}

	public void printCouponsDao() throws FileNotFoundException, IOException {
		couponDaoImpl.printCoupons();
	}

	public void clearDatabase() {
		couponDaoImpl.clearDatabase();
	}
	public void lookForWinnerBigLotto(int[] result) throws FileNotFoundException, IOException{
		couponDaoImpl.lookForWinner(result, Game.BIG);
	}
	public void lookForWinnerSmallLotto(int[] result) throws FileNotFoundException, IOException{
		couponDaoImpl.lookForWinner(result, Game.SMALL);
	}
	public void lookForWinnerMultiLotto(int[] result) throws FileNotFoundException, IOException{
		couponDaoImpl.lookForWinner(result, Game.MULTI);
	}
	
	public void printStatistics() throws FileNotFoundException, IOException {
		couponDaoImpl.printStatistics();
	}
	
	Coupon sellNewCoupon() {
		Coupon coupon = new Coupon();
		return coupon;
	}

	public void printCoupons() {
		for (Coupon coupon : coupons) {
			System.out.println("Coupon's id: " + coupon.getCouponId());
			Map<String, int[]> bets = coupon.getBets();
			bets.forEach((k, v) -> System.out.println(k + " " + Arrays.toString(v))); // k.substring(2) - only game name
			System.out.println();
		}
	}

	public void addToCoupons(Coupon coupon) {
		coupons.add(coupon);
	}
}
