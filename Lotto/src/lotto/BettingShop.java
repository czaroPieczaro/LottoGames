package lotto;

import java.util.*;

public class BettingShop {
	private Map<String, Coupon> couponMap = new LinkedHashMap<String, Coupon>();

	void printCouponMap() {
		couponMap.forEach((k, v) -> printCoupon(v));
	}

	void printCoupon(Coupon coupon) {
		System.out.println("Client's id: " + coupon.getCouponId());
		Map<String, int[]> bets = coupon.getBets();
		bets.forEach((k, v) -> System.out.println(k + "  " + Arrays.toString(v))); // k.substring(2) - only game name
		System.out.println();
	}

	public void addToCouponMap(String couponId, Coupon coupon) {
		couponMap.put(couponId, coupon);
	}

	public BettingShop() {

	}
}
