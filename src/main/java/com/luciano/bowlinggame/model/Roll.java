package com.luciano.bowlinggame.model;

public class Roll {
	private static final String FOUL_SIGN = "F";
	private String value;
	private int pins;

	public Roll(String value) {
		super();
		this.value = value;
		if (FOUL_SIGN.equalsIgnoreCase(value)) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Roll other = (Roll) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
