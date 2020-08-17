package com.luciano.bowlinggame.service;

import java.util.List;
import java.util.Map;

import com.luciano.bowlinggame.model.Roll;

public interface FileService {

	/**
	 * Returns a maps of players and its lists of frames
	 *
	 * @param absolute path of the input file
	 * @return map of players and its frames
	 */
	Map<String, List<Roll>> readFile(String path);

}
