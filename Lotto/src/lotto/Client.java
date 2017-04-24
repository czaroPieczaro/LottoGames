package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Client {
	private List<Coupon> coupons = new ArrayList<Coupon>();

	public Client() {

	}

	public Coupon buy(Coupon coupon) {
		final int TOTAL_NUMBER_OF_BETS = ThreadLocalRandom.current().nextInt(1, 11);
		for (int i = 1; i <= TOTAL_NUMBER_OF_BETS; i++) {
			Game game = Game.randomGame(); //Client chooses the game
			int[] bet = new int[game.numberOfNumbersToChoose()];
			coupon.addBetToCoupon(i + "|" + game.gameName(), bet);
		}
		return coupon;
	}

	public void crossBets(Coupon coupon) {
		Map<String, int[]> bets = coupon.getBets();
		for (Map.Entry<String, int[]> entry : bets.entrySet()) {
			int index = entry.getKey().indexOf("|") + 1;
			Game game = Game.gameGeneratedWithName(entry.getKey().substring(index));
			for (int i = 0; i < game.numberOfNumbersToChoose(); i++) {
				boolean contains = true;
				while (contains == true) {
					int numberDrawn = ThreadLocalRandom.current().nextInt(game.minimum(), game.maximum());
					contains = IntStream.of(entry.getValue()).anyMatch(x -> x == numberDrawn);
					if (contains == false) {
						entry.getValue()[i] = numberDrawn;
					}
				}
			}
			Arrays.sort(entry.getValue());
			coupons.add(coupon);//client's private coupon list
		}
	}
}
