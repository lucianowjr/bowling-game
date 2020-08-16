package com.luciano.bowlinggame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.luciano.bowlinggame.controller.GameController;
import com.luciano.bowlinggame.controller.GameControllerImpl;
import com.luciano.bowlinggame.controller.OutputController;
import com.luciano.bowlinggame.controller.OutputControllerImpl;
import com.luciano.bowlinggame.exception.FileParsingException;
import com.luciano.bowlinggame.model.Player;

class BowlingGameApplicationTests {

	private static final String SAMPLE_FILE = "src/test/resources/sample.txt";
	private static final String PERFECT_FILE = "src/test/resources/perfect.txt";
	private static final String ZERO_FILE = "src/test/resources/zero.txt";

	private static final String SAMPLE_OUTPUT_FILE = "src/test/resources/samplescoreboard.txt";
	private static final String PERFECT_OUTPUT_FILE = "src/test/resources/perfectscoreboard.txt";
	private static final String ZERO_OUTPUT_FILE = "src/test/resources/zeroscoreboard.txt";

	private OutputController outputController;

	private GameController gameController;

	@BeforeEach
	public void setUpStreams() {
		outputController = new OutputControllerImpl();
		gameController = new GameControllerImpl();
	}

	@Test
	void integrationTest_SampleCase() throws IOException {

		List<Player> players = gameController.getPlayersScores(SAMPLE_FILE);
		String scoreboard = outputController.getScoreBoard(players);

		String fileContent = readFileAsString(SAMPLE_OUTPUT_FILE);
		assertEquals(fileContent, scoreboard);
	}

	@Test
	void integrationTest_PerfectCase() throws IOException {

		List<Player> players = gameController.getPlayersScores(PERFECT_FILE);
		String scoreboard = outputController.getScoreBoard(players);

		String fileContent = readFileAsString(PERFECT_OUTPUT_FILE);
		assertEquals(fileContent, scoreboard);
	}

	@Test
	void integrationTest_ZeroCase() throws IOException {

		List<Player> players = gameController.getPlayersScores(ZERO_FILE);
		String scoreboard = outputController.getScoreBoard(players);

		String fileContent = readFileAsString(ZERO_OUTPUT_FILE);
		assertEquals(fileContent, scoreboard);
	}

	private static String readFileAsString(String outputFile) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		try (Stream<String> stream = Files.lines(Paths.get(outputFile))) {
			stream.forEach(line -> {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			});
		} catch (IOException e) {
			throw new FileParsingException("An error occurred when reading the file. Please, check the path.");
		}

		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		return stringBuilder.toString();
	}

}
