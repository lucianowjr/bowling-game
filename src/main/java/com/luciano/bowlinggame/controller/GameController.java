package com.luciano.bowlinggame.controller;

import java.util.List;

import com.luciano.bowlinggame.model.Player;

public interface GameController {

	/**
	 * Returns a list of players and their lists of frames
	 *
	 * @param absolute path of the input file
	 * @return list of players and their frames
	 */
	List<Player> getPlayersScores(String filePath);

}
