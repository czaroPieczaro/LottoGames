package lotto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import sub.randomizing.machines.*;

public class LottoMain {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		long startTime = System.nanoTime();
		BettingShop bettingShop = new BettingShop();
		bettingShop.clearDatabase();
		bettingShop.clearStatisticsDatabase();
		int maximumNumberOfBetsOnCoupon;

/*		//Variant 1: 1 coupon per client, exactly one game from each kind

		for (int i = 1; i <= 100000; i++) {
			maximumNumberOfBetsOnCoupon = 3;
			Client client = new Client(i);
			Coupon coupon = bettingShop.sellNewCoupon(i);
			coupon = client.buy(coupon, maximumNumberOfBetsOnCoupon);
			client.crossBets(coupon);
			bettingShop.addToCoupons(coupon);
		}*/

		//Variant 2: 1 coupon per client, exactly one game from each kind + max 7 other

/*		for (int i = 1; i <= 100000; i++) {
			maximumNumberOfBetsOnCoupon = 10;
			Client client = new Client(i);
			Coupon coupon = bettingShop.sellNewCoupon(i);
			coupon = client.buy(coupon, maximumNumberOfBetsOnCoupon);
			client.crossBets(coupon);
			bettingShop.addToCoupons(coupon);
		}*/

		//Variant 3: max 3 coupons per client. On coupon exactly one game from each kind + max 7 other

		for (int i = 1; i <= 100000; i++) {
			maximumNumberOfBetsOnCoupon = 10;
			Client client = new Client(i);
			Coupon coupon;
			int numberOfCoupons = ThreadLocalRandom.current().nextInt(1, 4);
			for (int j = 1; j <= numberOfCoupons; j++) {
				coupon = bettingShop.sellNewCoupon(client.getClientId());
				coupon = client.buy(coupon, maximumNumberOfBetsOnCoupon);
				client.crossBets(coupon);
				bettingShop.addToCoupons(coupon);
			}
		}
		bettingShop.addCouponsToDatabase();
		BigLottoMachine bigLottoMachine = new BigLottoMachine();
		SmallLottoMachine smallLottoMachine = new SmallLottoMachine();
		MultiLottoMachine multiLottoMachine = new MultiLottoMachine();
		Result bigLottoResult = bigLottoMachine.draw();
		Result smallLottoResult = smallLottoMachine.draw();
		Result multiLottoResult = multiLottoMachine.draw();
		printResult(bigLottoResult);
		printResult(smallLottoResult);
		printResult(multiLottoResult);
		bettingShop.writeToStatistics(bigLottoResult.getResults());
		bettingShop.writeToStatistics(smallLottoResult.getResults());
		bettingShop.writeToStatistics(multiLottoResult.getResults());
		bettingShop.addStatistics();
		bettingShop.printStatistics();
		bettingShop.printBigWinners();

		long endTime = System.nanoTime();
		System.out.println("Time: " + (endTime - startTime) * Math.pow(10, -9) + " seconds");
	}

	private static void printResult(Result result) {
		System.out.println(result.getGameName() + " result ID: " + result.getId());
		System.out.println(result.getGameName() + " result date: " + result.getTimeOfDraw());
		System.out.println(result.getGameName() + " result numbers: " + result.getStringResults());
	}
}