package com.luciano.bowlinggame.controller;

import com.luciano.bowlinggame.model.Scoreboard;

public interface OutputController {

	/**
	 * Prints the game scoreboard on the console
	 *
	 * @param the scoreboard object
	 */
	void printScoreboard(Scoreboard scoreboard);

	/**
	 * Returns the game scoreboard
	 *
	 * @param the scoreboard object
	 * @return the game scoreboard text
	 */
	String getScoreboard(Scoreboard scoreboard);

}
