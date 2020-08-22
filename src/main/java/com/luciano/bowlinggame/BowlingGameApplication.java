package com.luciano.bowlinggame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.luciano.bowlinggame.controller.GameController;
import com.luciano.bowlinggame.controller.InputController;
import com.luciano.bowlinggame.controller.OutputController;
import com.luciano.bowlinggame.model.FileData;
import com.luciano.bowlinggame.model.Scoreboard;

@SpringBootApplication
public class BowlingGameApplication implements CommandLineRunner {

	@Autowired
	InputController inputController;

	@Autowired
	GameController gameController;

	@Autowired
	OutputController outputController;

	Logger logger = LoggerFactory.getLogger(BowlingGameApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BowlingGameApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (args.length > 0) {
			String filePath = new String();
			filePath = args[args.length - 1];
			process(filePath);
		} else {
			logger.error("No argument was found. Please, run the application with the file path on argument.");
		}
	}

	private void process(String filePath) {
		FileData fileData = inputController.getFileData(filePath);
		Scoreboard scoreboard = gameController.getScoreboard(fileData);
		outputController.printScoreboard(scoreboard);
	}

}
