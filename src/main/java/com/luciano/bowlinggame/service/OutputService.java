package com.luciano.bowlinggame.service;

import com.luciano.bowlinggame.model.Scoreboard;

public interface OutputService {

	/**
	 * Returns the final game scoreboard text
	 *
	 * @param the scoreboard object
	 * @return the game scoreboard text
	 */
	String getScoreboard(Scoreboard scoreboard);

}
