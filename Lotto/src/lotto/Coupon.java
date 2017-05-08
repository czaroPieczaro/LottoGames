package lotto;

import java.util.*;
import java.time.LocalDateTime;

public class Coupon {

	private Map<Integer, Bet> bets = new LinkedHashMap<Integer, Bet>();
	private final LocalDateTime COUPON_ID = LocalDateTime.now();
	private final int clientId;

	public Coupon(int clientId) {
		this.clientId = clientId;
	}

	public void addBetToCoupon(int betId, Bet bet) {
		bets.put(betId, bet);
	}

	public LocalDateTime getCouponId() {
		return COUPON_ID;
	}

	public int getClientId() {
		return clientId;
	}

	public Map<Integer, Bet> getBets() {
		return bets;
	}
}
