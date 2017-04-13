package lotto;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Coupon {
	private String couponId;
	private Map<String, int[]> bets = new LinkedHashMap<String, int[]>();

	public Coupon(String couponId, int numberOfBigLottoBets, int numberOfSmallLottoBets, int numberOfMultiLottoBets) {
		this.couponId = couponId;
		int remainingBets = numberOfSmallLottoBets + numberOfBigLottoBets + numberOfMultiLottoBets;
		int totalNumberOfBets = remainingBets;

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

	public void crossNthNumber(String betId, int maximum, int position) {
		boolean contains = true;
		
		while (contains == true) {
			int numberDrawn = ThreadLocalRandom.current().nextInt(1, maximum);
			contains = IntStream.of(bets.get(betId)).anyMatch(x -> x == numberDrawn);
			if (contains == false) {
				bets.get(betId)[position] = numberDrawn;
			}
		}
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
}
