package com.luciano.bowlinggame.model;

import java.util.ArrayList;
import java.util.List;

public class Frame {

	private List<Roll> rolls;
	private boolean isSpare;
	private boolean isStrike;
	private int score;

	public Frame() {
		super();
		this.rolls = new ArrayList<Roll>();
		this.isSpare = false;
		this.isStrike = false;
	}

	public List<Roll> getRolls() {
		return rolls;
	}

	public void setRolls(List<Roll> rolls) {
		this.rolls = rolls;
	}

	public boolean isSpare() {
		return isSpare;
	}

	public void setSpare(boolean isSpare) {
		this.isSpare = isSpare;
	}

	public boolean isStrike() {
		return isStrike;
	}

	public void setStrike(boolean isStrike) {
		this.isStrike = isStrike;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
