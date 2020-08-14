package com.luciano.bowlinggame;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.luciano.bowlinggame.controller.GameController;
import com.luciano.bowlinggame.controller.InputController;
import com.luciano.bowlinggame.controller.OutputController;
import com.luciano.bowlinggame.model.Player;

@SpringBootApplication
public class BowlingGameApplication implements CommandLineRunner {

	@Autowired
	InputController inputController;

	@Autowired
	GameController gameController;

	@Autowired
	OutputController outputController;

	public static void main(String[] args) {
		SpringApplication.run(BowlingGameApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		process();
	}

	private void process() {
		String filePath = inputController.getInputFilePath();
		List<Player> players = gameController.getPlayersScores(filePath);
		outputController.printScoreBoard(players);
	}

}
