package com.luciano.bowlinggame.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.luciano.bowlinggame.exception.FileParsingException;
import com.luciano.bowlinggame.model.Player;
import com.luciano.bowlinggame.model.Roll;
import com.luciano.bowlinggame.service.FileService;
import com.luciano.bowlinggame.service.GameService;

@Controller
public class GameControllerImpl implements GameController {

	@Autowired
	FileService fileService;

	@Autowired
	GameService gameService;

	@Override
	public List<Player> getPlayersScores(String filePath) {
		List<Player> players = new ArrayList<>();
		try {
			Map<String, List<Roll>> rollsMap = fileService.readFile(filePath);
			players = gameService.createGame(rollsMap);
		} catch (FileParsingException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		return players;
	}

}
