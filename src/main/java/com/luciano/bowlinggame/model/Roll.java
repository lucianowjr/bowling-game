package com.luciano.bowlinggame.model;

public class Roll {
	private static final String FOUL_SIGN = "f";
	private String value;
	private int pins;

	public Roll(String value) {
		super();
		this.value = value;
		if (FOUL_SIGN.equals(value.toLowerCase())) {
			this.pins = 0;
		} else {
			this.pins = Integer.parseInt(value);
		}
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getPins() {
		return pins;
	}

	public void setPins(int pins) {
		this.pins = pins;
	}

}
