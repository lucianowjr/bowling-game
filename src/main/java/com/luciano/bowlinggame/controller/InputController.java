package com.luciano.bowlinggame.controller;

import com.luciano.bowlinggame.model.FileData;

public interface InputController {

	/**
	 * Gets the input file data
	 * 
	 * @return The file data object
	 */
	FileData getFileData(String filePath);

}
