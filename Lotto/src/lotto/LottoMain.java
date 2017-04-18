package lotto;

import java.time.LocalDate;
import sub.randomizing.machines.*;
import java.time.format.DateTimeFormatter;

public class LottoMain {
	public static void main(String[] args) {

		BettingShop bettingShop = new BettingShop();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		for (int i = 1; i <= 4; i++) { //Many clients can be generated
			String id = i + "." + dtf.format(localDate);
			Client client = new Client(id);
			Coupon coupon = bettingShop.printNewCoupon(id);
			client.buy(bettingShop, coupon);
			bettingShop.addToCouponMap(id, coupon); // bettingShop saves the coupon in it's CouponMap
		}
		bettingShop.printCouponMap();
		
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
	}
}