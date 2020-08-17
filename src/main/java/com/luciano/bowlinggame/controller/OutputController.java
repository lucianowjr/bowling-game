package com.luciano.bowlinggame.controller;

import java.util.List;

import com.luciano.bowlinggame.model.Player;

public interface OutputController {

	/**
	 * Print the final game scoreboard on the console
	 *
	 * @param absolute path of the input file
	 */
	void printScoreBoard(List<Player> players);

	/**
	 * Returns the final game scoreboard
	 *
	 * @param list of players and its frames
	 * @return game scoreboard
	 */
	String getScoreBoard(List<Player> players);

}
