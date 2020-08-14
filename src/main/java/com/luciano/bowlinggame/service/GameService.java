package com.luciano.bowlinggame.service;

import java.util.List;
import java.util.Map;

import com.luciano.bowlinggame.model.Player;
import com.luciano.bowlinggame.model.Roll;

public interface GameService {

	public List<Player> createGame(Map<String, List<Roll>> rollsMap);

}
