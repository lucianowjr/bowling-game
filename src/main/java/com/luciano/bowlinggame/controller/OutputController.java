package com.luciano.bowlinggame.controller;

import java.util.List;

import com.luciano.bowlinggame.model.Player;

public interface OutputController {

	void printScoreBoard(List<Player> players);

	String getScoreBoard(List<Player> players);

}
