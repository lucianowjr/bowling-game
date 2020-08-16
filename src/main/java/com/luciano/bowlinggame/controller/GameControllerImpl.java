package com.luciano.bowlinggame.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.luciano.bowlinggame.exception.FileParsingException;
import com.luciano.bowlinggame.model.Player;
import com.luciano.bowlinggame.model.Roll;
import com.luciano.bowlinggame.service.FileService;
import com.luciano.bowlinggame.service.FileServiceImpl;
import com.luciano.bowlinggame.service.GameService;
import com.luciano.bowlinggame.service.GameServiceImpl;

@Controller
public class GameControllerImpl implements GameController {

	@Autowired
	FileService fileService;

	@Autowired
	GameService gameService;

	Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);

	public GameControllerImpl() {
		super();

		if (fileService == null) {
			fileService = new FileServiceImpl();
		}

		if (gameService == null) {
			gameService = new GameServiceImpl();
		}
	}

	@Override
	public List<Player> getPlayersScores(String filePath) {
		List<Player> players = new ArrayList<>();
		try {
			Map<String, List<Roll>> rollsMap = fileService.readFile(filePath);
			players = gameService.createGame(rollsMap);
		} catch (FileParsingException e) {
			logger.error(e.getMessage());
			System.exit(0);
		}
		return players;
	}

}
