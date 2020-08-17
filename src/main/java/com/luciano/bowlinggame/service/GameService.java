package com.luciano.bowlinggame.service;

import java.util.List;
import java.util.Map;

import com.luciano.bowlinggame.model.Player;
import com.luciano.bowlinggame.model.Roll;

public interface GameService {

	/**
	 * Returns a list of players and their lists of frames
	 *
	 * @param map of players and their lists of rolls
	 * @return list of players and their lists of frames
	 */
	List<Player> createGame(Map<String, List<Roll>> rollsMap);

}
