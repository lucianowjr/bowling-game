package com.luciano.bowlinggame.controller;

import com.luciano.bowlinggame.model.FileData;
import com.luciano.bowlinggame.model.Scoreboard;

public interface GameController {

	/**
	 * Returns the scoreboard
	 *
	 * @param The file data object
	 * @return The scoreboard objetc
	 */
	Scoreboard getScoreboard(FileData fileData);

}
