package com.luciano.bowlinggame.controller;

import java.util.List;

import com.luciano.bowlinggame.model.Player;

public interface OutputController {

	public void printScoreBoard(List<Player> players);

}
