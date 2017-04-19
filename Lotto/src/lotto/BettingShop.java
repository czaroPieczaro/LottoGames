package lotto;

import java.util.*;

public class BettingShop {
	private List<Coupon> coupons = new ArrayList<Coupon>();

	public BettingShop() {

	}

	Coupon printNewCoupon() {
		Coupon coupon = new Coupon();
		return coupon;
	}

	void printCoupons() {
		for (Coupon coupon : coupons) {
			printCoupon(coupon);
		}
	}

	void printCoupon(Coupon coupon) {
		System.out.println("Coupon's id: " + coupon.getCouponId());
		Map<String, int[]> bets = coupon.getBets();
		bets.forEach((k, v) -> System.out.println(k + " " + Arrays.toString(v))); // k.substring(2) - only game name
		System.out.println();
	}

	public void addToCoupons(Coupon coupon) {
		coupons.add(coupon);
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void lookForWinner(int[] result) {
		for (Coupon coupon : coupons) {
			for (Map.Entry<String, int[]> entry : coupon.getBets().entrySet()) {
				if (Arrays.equals(entry.getValue(), result) == true) {
					System.out.println("There's a winner!");
					System.out.println("Coupon ID: " + coupon.getCouponId() + " bet id = " + entry.getKey());
				}
			}

		}
	}
}
