package com.luciano.bowlinggame.model;

import java.util.List;
import java.util.Map;

public class FileData {

	Map<String, List<Roll>> rollsMap;

	public FileData(Map<String, List<Roll>> rollsMap) {
		super();
		this.rollsMap = rollsMap;
	}

	public Map<String, List<Roll>> getRollsMap() {
		return rollsMap;
	}

	public void setRollsMap(Map<String, List<Roll>> rollsMap) {
		this.rollsMap = rollsMap;
	}

}
