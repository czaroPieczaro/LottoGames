package lotto;

import java.io.FileNotFoundException;
import java.io.IOException;

import sub.randomizing.machines.*;

public class LottoMain {
	public static void main(String[] args) throws FileNotFoundException, IOException {

		BettingShop bettingShop = new BettingShop();
		bettingShop.clearDatabase();
		for (int i = 1; i <= 5; i++) {
			Client client = new Client();
			Coupon coupon = bettingShop.sellNewCoupon();
			coupon = client.buy(coupon);
			client.crossBets(coupon);
			bettingShop.addToCoupons(coupon); // bettingShop saves the coupon in it's coupons list
			bettingShop.addCouponToDatabase(coupon, client.getClientId()); //with use of DAO
		}
		//bettingShop.printCoupons();
		bettingShop.printCouponsDao(); //with use of DAO
		bettingShop.printStatistics(); //with use of DAO
		BigLottoMachine bigLottoMachine = new BigLottoMachine();
		SmallLottoMachine smallLottoMachine = new SmallLottoMachine();
		MultiLottoMachine multiLottoMachine = new MultiLottoMachine();
		Result bigLottoResult = bigLottoMachine.draw();
		Result smallLottoResult = smallLottoMachine.draw();
		Result multiLottoResult = multiLottoMachine.draw();
		printResult(bigLottoResult);
		printResult(smallLottoResult);
		printResult(multiLottoResult);
		bettingShop.lookForWinnerBigLotto(bigLottoResult.getResults());
		bettingShop.lookForWinnerSmallLotto(smallLottoResult.getResults());
		bettingShop.lookForWinnerMultiLotto(multiLottoResult.getResults());
	}

	private static void printResult(Result result) {
		System.out.println(result.getGameName() + " result ID: " + result.getId());
		System.out.println(result.getGameName() + " result date: " + result.getTimeOfDraw());
		System.out.println(result.getGameName() + " result numbers: " + result.getStringResults());
	}
}
