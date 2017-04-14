package lotto;

import java.util.*;

public class Coupon {
	private String couponId; // It's number on the coupon + game name
	private Map<String, int[]> bets = new LinkedHashMap<String, int[]>();

	public Coupon(String clientId, int numberOfBigLottoBets, int numberOfSmallLottoBets, int numberOfMultiLottoBets) {
		this.couponId = clientId;
		int totalNumberOfBets = numberOfSmallLottoBets + numberOfBigLottoBets + numberOfMultiLottoBets;
		int remainingBets = totalNumberOfBets;

		while (numberOfBigLottoBets > 0) {
			bets.put(totalNumberOfBets - remainingBets + 1 + " Big Lotto", new int[6]);
			remainingBets--;
			numberOfBigLottoBets--;
		}
		while (numberOfSmallLottoBets > 0) {
			bets.put(totalNumberOfBets - remainingBets + 1 + " Small Lotto", new int[5]);
			remainingBets--;
			numberOfSmallLottoBets--;
		}
		while (numberOfMultiLottoBets > 0) {
			bets.put(totalNumberOfBets - remainingBets + 1 + " Multi Lotto", new int[20]);
			remainingBets--;
			numberOfMultiLottoBets--;
		}
	}

	public String getCouponId() {
		return couponId;
	}

	public Map<String, int[]> getBets() {
		return bets;
	}
}
