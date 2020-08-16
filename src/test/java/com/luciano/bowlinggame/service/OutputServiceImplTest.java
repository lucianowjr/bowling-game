package com.luciano.bowlinggame.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.luciano.bowlinggame.model.Frame;
import com.luciano.bowlinggame.model.Player;
import com.luciano.bowlinggame.model.Roll;

class OutputServiceImplTest {

	private static String HEADER = "\nFrame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n";

	private OutputService outputService;

	@BeforeEach
	public void setUp() {
		outputService = new OutputServiceImpl();
	}

	@Test
	void testGetScoreboard_SampleScoreboard() {
		List<Player> players = new ArrayList<>();
		players.add(createSamplePlayer());
		String scoreboard = outputService.getScoreBoard(players);
		String testScoreboard = generateSampleScoreBoard();
		assertEquals(testScoreboard, scoreboard);
	}

	@Test
	void testGetScoreboard_PerfecScoreboard() {
		List<Player> players = new ArrayList<>();
		players.add(createPerfectPlayer());
		String scoreboard = outputService.getScoreBoard(players);
		String testScoreboard = generatePerfectScoreBoard();
		assertEquals(testScoreboard, scoreboard);
	}

	@Test
	void testGetScoreboard_ZeroScoreboard() {
		List<Player> players = new ArrayList<>();
		players.add(createZeroPlayer());
		String scoreboard = outputService.getScoreBoard(players);
		String testScoreboard = generateZeroScoreBoard();
		assertEquals(testScoreboard, scoreboard);
	}

	@Test
	void testGetScoreboard_SpareLastFrameScoreboard() {
		List<Player> players = new ArrayList<>();
		players.add(createSpareLastFramePlayer());
		String scoreboard = outputService.getScoreBoard(players);
		String testScoreboard = generateSpareLastFrameScoreBoard();
		assertEquals(testScoreboard, scoreboard);
	}

	private Player createSamplePlayer() {
		Player player = new Player("Jeff");
		player.setFrames(generateFramesSamplePlayerGame());
		return player;
	}

	private Player createPerfectPlayer() {
		Player player = new Player("Frank");
		player.setFrames(generateFramesPerfectGame());
		return player;
	}

	private Player createZeroPlayer() {
		Player player = new Player("Carl");
		player.setFrames(generateFramesZeroGame());
		return player;
	}

	private Player createSpareLastFramePlayer() {
		Player player = new Player("Carl");
		player.setFrames(generateFramesSpareLastFrameGame());
		return player;
	}

	private List<Frame> generateFramesSamplePlayerGame() {
		List<Frame> frames = new ArrayList<Frame>();

		Frame frame = new Frame();
		Roll roll = new Roll("10");
		frame.getRolls().add(roll);
		frame.setStrike(true);
		frame.setScore(20);
		frames.add(frame);

		frame = new Frame();
		roll = new Roll("7");
		frame.getRolls().add(roll);
		roll = new Roll("3");
		frame.getRolls().add(roll);
		frame.setSpare(true);
		frame.setScore(19);
		frames.add(frame);

		frame = new Frame();
		roll = new Roll("9");
		frame.getRolls().add(roll);
		roll = new Roll("0");
		frame.getRolls().add(roll);
		frame.setScore(9);
		frames.add(frame);

		frame = new Frame();
		roll = new Roll("10");
		frame.getRolls().add(roll);
		frame.setStrike(true);
		frame.setScore(18);
		frames.add(frame);

		frame = new Frame();
		roll = new Roll("0");
		frame.getRolls().add(roll);
		roll = new Roll("8");
		frame.getRolls().add(roll);
		frame.setScore(8);
		frames.add(frame);

		frame = new Frame();
		roll = new Roll("8");
		frame.getRolls().add(roll);
		roll = new Roll("2");
		frame.getRolls().add(roll);
		frame.setSpare(true);
		frame.setScore(10);
		frames.add(frame);

		frame = new Frame();
		roll = new Roll("F");
		frame.getRolls().add(roll);
		roll = new Roll("6");
		frame.getRolls().add(roll);
		frame.setScore(6);
		frames.add(frame);

		frame = new Frame();
		roll = new Roll("10");
		frame.getRolls().add(roll);
		frame.setStrike(true);
		frame.setScore(30);
		frames.add(frame);

		frame = new Frame();
		roll = new Roll("10");
		frame.getRolls().add(roll);
		frame.setStrike(true);
		frame.setScore(28);
		frames.add(frame);

		frame = new Frame();
		roll = new Roll("10");
		frame.getRolls().add(roll);
		roll = new Roll("8");
		frame.getRolls().add(roll);
		roll = new Roll("1");
		frame.getRolls().add(roll);
		frame.setStrike(true);
		frame.setScore(19);
		frames.add(frame);

		return frames;
	}

	private List<Frame> generateFramesPerfectGame() {
		List<Frame> frames = new ArrayList<Frame>();
		Roll roll = new Roll("10");
		for (int i = 0; i < 10; i++) {
			Frame frame = new Frame();
			frame.setStrike(true);
			frame.getRolls().add(roll);
			if (i == 9) {
				frame.getRolls().add(roll);
				frame.getRolls().add(roll);
			}
			frame.setScore(30);
			frames.add(frame);
		}
		return frames;
	}

	private List<Frame> generateFramesZeroGame() {
		List<Frame> frames = new ArrayList<Frame>();
		Roll roll = new Roll("0");
		for (int i = 0; i < 10; i++) {
			Frame frame = new Frame();
			frame.getRolls().add(roll);
			frame.getRolls().add(roll);
			frame.setScore(0);
			frames.add(frame);
		}
		return frames;
	}

	private List<Frame> generateFramesSpareLastFrameGame() {
		List<Frame> frames = new ArrayList<Frame>();
		Roll roll = new Roll("0");
		for (int i = 0; i < 9; i++) {
			Frame frame = new Frame();
			frame.getRolls().add(roll);
			frame.getRolls().add(roll);
			frame.setScore(0);
			frames.add(frame);
		}

		Frame frame = new Frame();
		roll = new Roll("0");
		frame.getRolls().add(roll);
		roll = new Roll("10");
		frame.getRolls().add(roll);
		roll = new Roll("2");
		frame.getRolls().add(roll);
		frame.setScore(12);
		frame.setSpare(true);
		frames.add(frame);

		return frames;
	}

	private String generateSampleScoreBoard() {
		StringBuilder sb = new StringBuilder();
		sb.append(HEADER);
		sb.append("Jeff\n");
		sb.append("Pinfalls");
		sb.append("\t\tX");
		sb.append("\t7\t/");
		sb.append("\t9\t0");
		sb.append("\t\tX");
		sb.append("\t0\t8");
		sb.append("\t8\t/");
		sb.append("\tF\t6");
		sb.append("\t\tX");
		sb.append("\t\tX");
		sb.append("\tX\t8\t1\n");
		sb.append("Score\t\t");
		sb.append("20\t\t39\t\t48\t\t66\t\t74\t\t84\t\t90\t\t120\t\t148\t\t167\n");
		return sb.toString();
	}

	private String generatePerfectScoreBoard() {
		StringBuilder sb = new StringBuilder();
		sb.append(HEADER);
		sb.append("Frank\n");
		sb.append("Pinfalls");
		for (int i = 0; i < 9; i++) {
			sb.append("\t\tX");
		}
		sb.append("\tX");
		sb.append("\tX");
		sb.append("\tX\n");
		sb.append("Score\t\t");
		sb.append("30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300\n");
		return sb.toString();
	}

	private String generateZeroScoreBoard() {
		StringBuilder sb = new StringBuilder();
		sb.append(HEADER);
		sb.append("Carl\n");
		sb.append("Pinfalls");
		for (int i = 0; i < 10; i++) {
			sb.append("\t0");
			sb.append("\t0");
		}
		sb.append("\n");
		sb.append("Score\t\t");
		sb.append("0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\n");
		return sb.toString();
	}

	private String generateSpareLastFrameScoreBoard() {
		StringBuilder sb = new StringBuilder();
		sb.append(HEADER);
		sb.append("Carl\n");
		sb.append("Pinfalls");
		for (int i = 0; i < 9; i++) {
			sb.append("\t0");
			sb.append("\t0");
		}
		sb.append("\t0");
		sb.append("\t/");
		sb.append("\t2");
		sb.append("\n");
		sb.append("Score\t\t");
		sb.append("0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t12\n");
		return sb.toString();
	}

}
