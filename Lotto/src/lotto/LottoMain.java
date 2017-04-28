package lotto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import sub.randomizing.machines.*;

public class LottoMain {
	public static void main(String[] args) throws FileNotFoundException, IOException {

		BettingShop bettingShop = new BettingShop();
		bettingShop.clearDatabase();
		bettingShop.clearStatisticsDatabase();
		
		//Variant 1: 1 coupon per client, exactly one game from each kind
		
		for (int i = 1; i <= 100000; i++) {
			Client client = new Client();
			Coupon coupon = bettingShop.sellNewCoupon();
			coupon = client.buy(coupon, 3);
			client.crossBets(coupon);
			bettingShop.addToCoupons(coupon,client.getClientId());
		}

		//Variant 2: 1 coupon per client, exactly one game from each kind + max 7 other

		
/*		 for (int i = 1; i <= 100000; i++) {
		 Client client = new Client();
		 Coupon coupon = bettingShop.sellNewCoupon();
		 coupon = client.buy(coupon,10);
		 client.crossBets(coupon);
		 bettingShop.addToCoupons(coupon,client.getClientId());
		 }*/
		 
		//Variant 3: max 3 coupons per client. On coupon exactly one game from each kind + max 7 other

		
/*		 for (int i = 1; i <= 100000; i++) {
			 Client client = new Client();
			 Coupon coupon;
			 int numberOfCoupons = ThreadLocalRandom.current().nextInt(1, 4);
			 for (int j = 1; j <= numberOfCoupons; j++) {
			 coupon = bettingShop.sellNewCoupon();
			 coupon = client.buy(coupon, 10);
			 client.crossBets(coupon);
			 bettingShop.addToCoupons(coupon,client.getClientId());
			 }
		 }*/
		 
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
		bettingShop.clearStatisticsDatabase();
		bettingShop.getStatistics(bigLottoResult.getResults(),smallLottoResult.getResults(),multiLottoResult.getResults());
		bettingShop.printStatistics();
		bettingShop.printBigWinners();
	}

	private static void printResult(Result result) {
		System.out.println(result.getGameName() + " result ID: " + result.getId());
		System.out.println(result.getGameName() + " result date: " + result.getTimeOfDraw());
		System.out.println(result.getGameName() + " result numbers: " + result.getStringResults());
	}
}