package lotto;

import java.util.concurrent.ThreadLocalRandom;

public class Client {
	private String clientId;
	private Coupon coupon;

	public Client(String clientId) {
		this.clientId = clientId;
	}

	public void buy(BettingShop bettingShop) {
		int totalNumberOfBets = ThreadLocalRandom.current().nextInt(1, 11); // Choosing
																			// total
																			// number
																			// of
																			// bets
		int numberOfSmallLottoBets = 0; // number of small lotto bets. Must be
										// inititalized with zero value,
										// otherwise it may not be initialized
										// at all.
		int numberOfMultiLottoBets = 0;
		int numberOfBigLottoBets = 0;

		for (int i = 0; i < totalNumberOfBets; i++) { // in this loop we
														// randomly select the
														// games that will
														// appear on the coupon.
			int rand = ThreadLocalRandom.current().nextInt(1, 4);// 1 is for Big
																	// Lotto, 2
																	// is for
																	// small
																	// Lotto and
																	// 3 is for
																	// Multi
																	// Lotto
			if (rand == 1) {
				numberOfBigLottoBets++;
			} else if (rand == 2) {
				numberOfSmallLottoBets++;
			} else if (rand == 3) {
				numberOfMultiLottoBets++;
			}
		}
		// couponId = clientId we use clientId (which is passed as a parameter
		// when instantizing class Client) as a couponId (which is needed to
		// instantiate class Coupon)

		// Below we create a blank coupon with couponId = clientId and arrays
		// initialy filled with zeros which represent certain blank coupons for
		// games
		// that were chosen randomly before
		Coupon coupon = new Coupon(clientId, numberOfBigLottoBets, numberOfSmallLottoBets, numberOfMultiLottoBets);
		// Now the client fills randomly the coupon. Firstly big lotto, then
		// small lotto and then multi lotto
		for (int i = 1; i <= numberOfBigLottoBets; i++) {
			int j = 0;
			while (j < 6) {
				coupon.crossNthNumber(i + " Big Lotto", 49, j); // client
																// chooses jth
																// number in the
																// ith bet, 49
																// is maximum
																// number we can
																// choose in Big
																// Lotto
				j++;
			}
		}
		for (int i = 1; i <= numberOfSmallLottoBets; i++) {
			int j = 0;
			while (j < 5) {
				coupon.crossNthNumber(numberOfBigLottoBets + i + " Small Lotto", 35, j);
				j++;
			}
		}
		for (int i = 1; i <= numberOfMultiLottoBets; i++) {
			int j = 0;
			while (j < 20) {
				coupon.crossNthNumber(numberOfBigLottoBets + numberOfSmallLottoBets + i + " Multi Lotto", 80, j);
				j++;
			}
		}

		bettingShop.addToCouponMap(clientId, coupon); // bettingShop saves the
														// coupon in it's
														// CouponMap
		this.coupon = coupon; // We give a coupon to the client
	}

	public Coupon getCoupon() {
		return coupon;
	}
}
