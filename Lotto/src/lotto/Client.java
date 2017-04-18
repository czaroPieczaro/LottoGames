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

	public Coupon buy(BettingShop bettingShop, Coupon coupon) {
		final int TOTAL_NUMBER_OF_BETS = ThreadLocalRandom.current().nextInt(1, 11);
		for (int i=1; i<=TOTAL_NUMBER_OF_BETS;i++){
			int gameNumber = ThreadLocalRandom.current().nextInt(1, 4);
			addBet(coupon, gameNumber, i);
		}
		return coupon;
	}

	public void addBet(Coupon coupon, int gameNumber, int betNumber) {
		int numberOfDrawnNumbers;
		int maximum;
		String gameName;
		if (gameNumber == 1) {
			gameName = "Big Lotto";
			numberOfDrawnNumbers = 6;
			maximum = 49;

		} else if (gameNumber == 2) {
			gameName = "Small Lotto";
			numberOfDrawnNumbers = 5;
			maximum = 35;
		} else {
			gameName = "Multi Lotto";
			numberOfDrawnNumbers = 20;
			maximum = 80;
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
		coupon.getBets().put(betNumber + " " + gameName, bet);
	}
}
