package lotto;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Result {
	private String gameName;
	private LocalDateTime timeOfDraw;
	private String id;
	private int[] results;

	public Result(String gameName, int[] results) {
		this.gameName = gameName;
		this.timeOfDraw=LocalDateTime.now();
		this.id=gameName + " " + timeOfDraw;
		this.results=results;
	}

	public LocalDateTime getTimeOfDraw() {
		return timeOfDraw;
	}
	public String getId() {
		return id;
	}
	
	public String getGameName() {
		return gameName;
	}

	public int[] getResults() {
		return results;
	}
	public String getStringResults() {
		return Arrays.toString(results);
	}

}
