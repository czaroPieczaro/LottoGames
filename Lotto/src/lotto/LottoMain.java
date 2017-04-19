package lotto;

import sub.randomizing.machines.*;

public class LottoMain {
	public static void main(String[] args) {

		BettingShop bettingShop = new BettingShop();
		for (int i = 1; i <= 3; i++) { //Many clients can be generated
			Client client = new Client();
			Coupon coupon = bettingShop.printNewCoupon();
			client.buy(bettingShop, coupon);
			bettingShop.addToCoupons(coupon); // bettingShop saves the coupon in it's CouponMap
		}
		bettingShop.printCoupons();

		BigLottoMachine bigLottoMachine = new BigLottoMachine();
		SmallLottoMachine smallLottoMachine = new SmallLottoMachine();
		MultiLottoMachine multiLottoMachine = new MultiLottoMachine();
		Result bigLottoResult = bigLottoMachine.draw();
		Result smallLottoResult = smallLottoMachine.draw();
		Result multiLottoResult = multiLottoMachine.draw();
		System.out.println("Big lotto result ID: " + bigLottoResult.getId());
		System.out.println("Big lotto result date: " + bigLottoResult.getTimeOfDraw());
		System.out.println("Big lotto result numbers: " + bigLottoResult.getStringResults());
		System.out.println("Small lotto result ID: " + smallLottoResult.getId());
		System.out.println("Small lotto result date: " + smallLottoResult.getTimeOfDraw());
		System.out.println("Small lotto result numbers: " + smallLottoResult.getStringResults());
		System.out.println("Multi lotto result ID: " + multiLottoResult.getId());
		System.out.println("Multi lotto result date: " + multiLottoResult.getTimeOfDraw());
		System.out.println("Multi lotto result numbers: " + multiLottoResult.getStringResults());
		bettingShop.lookForWinner(bigLottoResult.getResults());
		bettingShop.lookForWinner(multiLottoResult.getResults());
		bettingShop.lookForWinner(smallLottoResult.getResults());
	}
}