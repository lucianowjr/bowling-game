package com.luciano.bowlinggame.service;

import java.util.List;

import com.luciano.bowlinggame.model.Player;

public interface OutputService {

	String getScoreBoard(List<Player> players);

}
