package sub.randomizing.machines;

public class SmallLottoMachine extends SuperRandomizingMachine {
	public SmallLottoMachine() {
		this.gameName = "Small Lotto";
		this.maximum = 35;
		this.numberOfDrawnNumbers = 5;
	}
}