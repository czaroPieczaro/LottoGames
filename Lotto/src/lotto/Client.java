package lotto;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Client {
	private String clientId;
	private Coupon coupon;

	public Client(String clientId) {
		this.clientId = clientId;
	}

	public void buy(BettingShop bettingShop) {
		final int TOTAL_NUMBER_OF_BETS = ThreadLocalRandom.current().nextInt(1, 11);
		int numberOfSmallLottoBets = 0;
		int numberOfMultiLottoBets = 0;
		int numberOfBigLottoBets = 0;
		/*
		 * in this loop we randomly select the games that will appear on the
		 * coupon. 1 is for Big Lotto, 2 is for Small Lotto and 3 is for Multi Lotto.
		 */
		for (int i = 0; i < TOTAL_NUMBER_OF_BETS; i++) {
			int gameId = ThreadLocalRandom.current().nextInt(1, 4);
			if (gameId == 1) {
				numberOfBigLottoBets++;
			} else if (gameId == 2) {
				numberOfSmallLottoBets++;
			} else {
				numberOfMultiLottoBets++;
			}
		}
		/*
		 * Below we give client a blank coupon with couponId = clientId and
		 * arrays initially filled with zeros which represent blank coupons for
		 * games that were chosen randomly before.
		 */
		this.coupon = new Coupon(clientId, numberOfBigLottoBets, numberOfSmallLottoBets, numberOfMultiLottoBets);
		/*
		 * Now the client fills randomly the coupon. Firstly big lotto, then
		 * small lotto and then multi lotto.Client chooses one of 6 (bet array
		 * size for Big Lotto, 5 for Small Lotto, 20 for Multi Lotto)
		 * numbers for jth field in the ith bet, 49 is
		 * maximum number we can choose in Big Lotto, 35 is for Small Lotto and
		 * 80 is for Multi Lotto. Minimal
		 * number is common for all games and it's 1.
		 */
		for (int i = 1; i <= numberOfBigLottoBets; i++) {
			int j = 0;
			while (j < 6) {
				/*
				 * 
				 */
				crossNthNumber(i + " Big Lotto", 49, j);
				j++;
			}
		}
		for (int i = 1; i <= numberOfSmallLottoBets; i++) {
			int j = 0;
			while (j < 5) {
				crossNthNumber(numberOfBigLottoBets + i + " Small Lotto", 35, j);
				j++;
			}
		}
		for (int i = 1; i <= numberOfMultiLottoBets; i++) {
			int j = 0;
			while (j < 20) {
				crossNthNumber(numberOfBigLottoBets + numberOfSmallLottoBets + i + " Multi Lotto", 80, j);
				j++;
			}
		}
		bettingShop.addToCouponMap(clientId, coupon); // bettingShop saves the coupon in it's CouponMap
	}

	public void crossNthNumber(String betId, int maximum, int position) {
		boolean contains = true;

		while (contains == true) {
			Map<String, int[]> bets = coupon.getBets();
			int numberDrawn = ThreadLocalRandom.current().nextInt(1, maximum);
			contains = IntStream.of(bets.get(betId)).anyMatch(x -> x == numberDrawn);
			if (contains == false) {
				bets.get(betId)[position] = numberDrawn;
			}
		}
	}
}
