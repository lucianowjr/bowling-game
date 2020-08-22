package com.luciano.bowlinggame.service;

import java.util.List;
import java.util.Map;

import com.luciano.bowlinggame.model.Roll;

public interface InputService {

	/**
	 * Reads the input file and put the file data into a Map object
	 * 
	 * @return A Map of players and their rolls
	 */
	Map<String, List<Roll>> readFile(String path);

}
