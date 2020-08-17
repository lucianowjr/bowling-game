package com.luciano.bowlinggame.service;

import java.util.List;

import com.luciano.bowlinggame.model.Player;

public interface OutputService {

	/**
	 * Returns the final game scoreboard
	 *
	 * @param list of players and their score frames
	 * @return game scoreboard
	 */
	String getScoreBoard(List<Player> players);

}
