package com.luciano.bowlinggame.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luciano.bowlinggame.exception.InvalidGameException;
import com.luciano.bowlinggame.model.FileData;
import com.luciano.bowlinggame.model.Player;
import com.luciano.bowlinggame.model.Roll;
import com.luciano.bowlinggame.model.Scoreboard;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private ScoreService scoreService;

	public GameServiceImpl() {
		super();
		if (scoreService == null) {
			scoreService = new ScoreServiceImpl();
		}
	}

	Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);

	@Override
	public Scoreboard createScoreboard(FileData fileData) {
		List<Player> players = new ArrayList<>();
		Map<String, List<Roll>> rollsMap = fileData.getRollsMap();

		try {
			for (Map.Entry<String, List<Roll>> entry : rollsMap.entrySet()) {
				Player player = new Player(entry.getKey());
				player.setFrames(scoreService.calculateScore(entry.getValue()));
				players.add(player);
			}
		} catch (InvalidGameException e) {
			logger.error(e.getMessage());
			System.exit(0);
		}

		Scoreboard scoreboard = new Scoreboard();
		scoreboard.setPlayers(players);

		return scoreboard;
	}

}
