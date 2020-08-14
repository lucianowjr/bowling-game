package com.luciano.bowlinggame.controller;

import java.util.List;

import com.luciano.bowlinggame.model.Player;

public interface GameController {

	public List<Player> getPlayersScores(String filePath);

}
