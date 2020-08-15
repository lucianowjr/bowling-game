package com.luciano.bowlinggame.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.luciano.bowlinggame.model.Frame;
import com.luciano.bowlinggame.model.Player;
import com.luciano.bowlinggame.model.Roll;

class OutputServiceImplTest {

	private static OutputService outputService;

	@BeforeAll
	public static void setUp() {
		outputService = new OutputServiceImpl();
	}

	@Test
	public void testGetScoreboard_PerfecScoreboard() {
		List<Player> players = new ArrayList<>();
		players.add(createPerfectPlayer());
		String scoreboard = outputService.getScoreBoard(players);
		String perfectScoreboard = generatePerfectScoreBoard();
		assertEquals(perfectScoreboard, scoreboard);
	}

	private Player createPerfectPlayer() {
		Player player = new Player("Frank");
		player.setFrames(generateFramesPerfectGame());
		return player;

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

	private String generatePerfectScoreBoard() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\nFrame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n");
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

}
