package sub.randomizing.machines;

public class BigLottoMachine extends SuperRandomizingMachine {
	public BigLottoMachine() {
		this.gameName = "Big Lotto";
		this.maximum = 49;
		this.numberOfDrawnNumbers = 6;
	}
	public void test(){
		System.out.println("BIG");
	}
}