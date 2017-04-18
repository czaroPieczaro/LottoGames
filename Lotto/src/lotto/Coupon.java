package lotto;

import java.util.*;

public class Coupon {
	private String couponId; // It's number on the coupon + game name
	private Map<String, int[]> bets = new LinkedHashMap<String, int[]>();

	public Coupon(String couponId){
		this.couponId = couponId;
	}

	public String getCouponId() {
		return couponId;
	}

	public Map<String, int[]> getBets() {
		return bets;
	}
}
