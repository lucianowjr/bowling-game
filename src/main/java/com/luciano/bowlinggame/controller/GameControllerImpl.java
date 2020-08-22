package com.luciano.bowlinggame.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.luciano.bowlinggame.model.FileData;
import com.luciano.bowlinggame.model.Scoreboard;
import com.luciano.bowlinggame.service.GameService;
import com.luciano.bowlinggame.service.GameServiceImpl;

@Controller
public class GameControllerImpl implements GameController {

	@Autowired
	GameService gameService;

	Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);

	public GameControllerImpl() {
		super();
		if (gameService == null) {
			gameService = new GameServiceImpl();
		}
	}

	@Override
	public Scoreboard getScoreboard(FileData fileData) {
		Scoreboard scoreboard = gameService.createScoreboard(fileData);
		return scoreboard;
	}

}
