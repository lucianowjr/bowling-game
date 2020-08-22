package com.luciano.bowlinggame.service;

import com.luciano.bowlinggame.model.FileData;
import com.luciano.bowlinggame.model.Scoreboard;

public interface GameService {

	/**
	 * Returns the game scoreboard
	 *
	 * @param the file data object
	 * @return the scoreboard object
	 */
	Scoreboard createScoreboard(FileData fileData);

}
