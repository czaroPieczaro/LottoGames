package lotto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Client {
	private List<Coupon> coupons = new ArrayList<Coupon>();
	private LocalDateTime clientId = LocalDateTime.now();

	public Client() {

	}

	public Coupon buy(Coupon coupon) {
		final int TOTAL_NUMBER_OF_BETS = ThreadLocalRandom.current().nextInt(1, 11);
		for (int i = 1; i <= TOTAL_NUMBER_OF_BETS; i++) {
			Bet bet = new Bet(); //Client chooses the game
			coupon.addBetToCoupon(i, bet);
		}
		return coupon;
	}

	public void crossBets(Coupon coupon) {
		Map<Integer, Bet> bets = coupon.getBets();
		for (Map.Entry<Integer, Bet> entry : bets.entrySet()) {
			int[] numbers = entry.getValue().getNumbers();
			Game game = entry.getValue().getGame();
			for (int i = 0; i < numbers.length; i++) {
				boolean contains = true;
				while (contains == true) {
					int numberDrawn = ThreadLocalRandom.current().nextInt(game.minimum(), game.maximum());
					contains = IntStream.of(numbers).anyMatch(x -> x == numberDrawn);
					if (contains == false) {
						numbers[i] = numberDrawn;
					}
				}
			}
			Arrays.sort(numbers);
			coupons.add(coupon);
		}
	}

	public LocalDateTime getClientId() {
		return clientId;
	}
}
