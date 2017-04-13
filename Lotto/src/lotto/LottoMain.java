package lotto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class LottoMain {
	public static void main(String[] args) {
		BettingShop bettingShop = new BettingShop();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		for (int i = 1; i <= 1; i++) {
			Client client = new Client(i +"."+ dtf.format(localDate));
			client.buy(bettingShop);
		}
		bettingShop.printCouponMap();
	}
}