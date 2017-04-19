package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.time.LocalDateTime;

public class Client {
	private List<Coupon> coupons = new ArrayList<Coupon>();
	private final LocalDateTime CLIENT_ID = LocalDateTime.now();
	private final String GAME_NAME_BIG_LOTTO = "Big Lotto";
	private final String GAME_NAME_SMALL_LOTTO = "Small Lotto";
	private final String GAME_NAME_MULTI_LOTTO = "Multi Lotto";
	private final int NUMBER_OF_CHOSEN_NUMBERS_BIG_LOTTO = 6;
	private final int NUMBER_OF_CHOSEN_NUMBERS_SMALL_LOTTO = 5;
	private final int NUMBER_OF_CHOSEN_NUMBERS_MULTI_LOTTO = 20;
	private final int MAXIMUM_BIG_LOTTO = 49;
	private final int MAXIMUM_SMALL_LOTTO = 35;
	private final int MAXIMUM_MULTI_LOTTO = 80;

	public Client() {

	}

	public Coupon buy(BettingShop bettingShop, Coupon coupon) {
		final int TOTAL_NUMBER_OF_BETS = ThreadLocalRandom.current().nextInt(1, 11);

		for (int i = 1; i <= TOTAL_NUMBER_OF_BETS; i++) {
			int gameNumber = ThreadLocalRandom.current().nextInt(1, 4);
			addBet(coupon, gameNumber, i);
		}
		coupons.add(coupon);
		return coupon;
	}

	public void addBet(Coupon coupon, int gameNumber, int betNumber) {
		int numberOfDrawnNumbers;
		int maximum;
		String gameName;
		if (gameNumber == 1) {
			gameName = GAME_NAME_BIG_LOTTO;
			numberOfDrawnNumbers = NUMBER_OF_CHOSEN_NUMBERS_BIG_LOTTO;
			maximum = MAXIMUM_BIG_LOTTO;
		} else if (gameNumber == 2) {
			gameName = GAME_NAME_SMALL_LOTTO;
			numberOfDrawnNumbers = NUMBER_OF_CHOSEN_NUMBERS_SMALL_LOTTO;
			maximum = MAXIMUM_SMALL_LOTTO;
		} else {
			gameName = GAME_NAME_MULTI_LOTTO;
			numberOfDrawnNumbers = NUMBER_OF_CHOSEN_NUMBERS_MULTI_LOTTO;
			maximum = MAXIMUM_MULTI_LOTTO;
		}
		int[] bet = new int[numberOfDrawnNumbers];
		for (int i = 0; i < numberOfDrawnNumbers; i++) {
			boolean contains = true;
			while (contains == true) {
				int numberDrawn = ThreadLocalRandom.current().nextInt(1, maximum);
				contains = IntStream.of(bet).anyMatch(x -> x == numberDrawn);
				if (contains == false) {
					bet[i] = numberDrawn;
				}
			}
		}
		Arrays.sort(bet);
		coupon.addBetToCoupon(betNumber + " " + gameName, bet);
	}
}
