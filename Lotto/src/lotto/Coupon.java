package lotto;

import java.util.*;
import java.time.LocalDateTime;

public class Coupon {

	private Map<String, int[]> bets = new LinkedHashMap<String, int[]>();
	private final LocalDateTime COUPON_ID = LocalDateTime.now();

	public Coupon() {
	}

	public void addBetToCoupon(String betId, int[] bet) {
		bets.put(betId, bet);
	}

	public LocalDateTime getCouponId() {
		return COUPON_ID;
	}

	public Map<String, int[]> getBets() {
		return bets;
	}
}
