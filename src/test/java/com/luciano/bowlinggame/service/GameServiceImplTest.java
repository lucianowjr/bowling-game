package com.luciano.bowlinggame.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.luciano.bowlinggame.model.Frame;
import com.luciano.bowlinggame.model.Player;
import com.luciano.bowlinggame.model.Roll;
import com.luciano.bowlinggame.validator.GameValidator;

class GameServiceImplTest {

	private static int MAX_SCORE_FRAME = 30;
	private static int MIN_SCORE_FRAME = 0;

	@InjectMocks
	private GameServiceImpl gameService;

	@Mock
	private GameValidator gameValidator;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testCreateSampleGame() {
		Map<String, List<Roll>> rollsMap = new HashMap<>();
		rollsMap.put("Jeff", generateSampleRolls());
		List<Player> players = gameService.createGame(rollsMap);

		List<Frame> frames = players.get(0).getFrames();

		assertEquals(20, frames.get(0).getScore());
		assertEquals(19, frames.get(1).getScore());
		assertEquals(9, frames.get(2).getScore());
		assertEquals(18, frames.get(3).getScore());
		assertEquals(8, frames.get(4).getScore());
		assertEquals(10, frames.get(5).getScore());
		assertEquals(6, frames.get(6).getScore());
		assertEquals(30, frames.get(7).getScore());
		assertEquals(28, frames.get(8).getScore());
		assertEquals(19, frames.get(9).getScore());
	}

	@Test
	void testCreatePerfectGame() {
		Map<String, List<Roll>> rollsMap = new HashMap<>();
		rollsMap.put("Carl", generatePerfectRolls());
		List<Player> players = gameService.createGame(rollsMap);
		for (Frame frame : players.get(0).getFrames()) {
			assertEquals(frame.getScore(), MAX_SCORE_FRAME);
		}
	}

	@Test
	void testCreateZeroGame() {
		Map<String, List<Roll>> rollsMap = new HashMap<>();
		rollsMap.put("Hugo", generateZeroRolls());
		List<Player> players = gameService.createGame(rollsMap);
		for (Frame frame : players.get(0).getFrames()) {
			assertEquals(frame.getScore(), MIN_SCORE_FRAME);
		}
	}

	@Test
	void testCreateZeroGameSpareLastFrame() {
		Map<String, List<Roll>> rollsMap = new HashMap<>();
		rollsMap.put("Carl", generateZeroRollsSpareLastFrame());
		List<Player> players = gameService.createGame(rollsMap);
		List<Frame> frames = players.get(0).getFrames();
		for (int i = 0; i < 9; i++) {
			assertEquals(MIN_SCORE_FRAME, frames.get(i).getScore());
		}
		assertEquals(12, frames.get(9).getScore());
	}

	private List<Roll> generateSampleRolls() {
		List<Roll> rolls = new ArrayList<>();

		Roll roll = new Roll("10");
		rolls.add(roll);
		roll = new Roll("7");
		rolls.add(roll);
		roll = new Roll("3");
		rolls.add(roll);
		roll = new Roll("9");
		rolls.add(roll);
		roll = new Roll("0");
		rolls.add(roll);
		roll = new Roll("10");
		rolls.add(roll);
		roll = new Roll("0");
		rolls.add(roll);
		roll = new Roll("8");
		rolls.add(roll);
		roll = new Roll("8");
		rolls.add(roll);
		roll = new Roll("2");
		rolls.add(roll);
		roll = new Roll("F");
		rolls.add(roll);
		roll = new Roll("6");
		rolls.add(roll);
		roll = new Roll("10");
		rolls.add(roll);
		roll = new Roll("10");
		rolls.add(roll);
		roll = new Roll("10");
		rolls.add(roll);
		roll = new Roll("8");
		rolls.add(roll);
		roll = new Roll("1");
		rolls.add(roll);
		return rolls;
	}

	private List<Roll> generatePerfectRolls() {
		List<Roll> rolls = new ArrayList<>();
		Roll roll = new Roll("10");
		for (int i = 0; i < 12; i++) {
			rolls.add(roll);
		}
		return rolls;
	}

	private List<Roll> generateZeroRolls() {
		List<Roll> rolls = new ArrayList<>();
		Roll roll = new Roll("0");
		for (int i = 0; i < 20; i++) {
			rolls.add(roll);
		}
		return rolls;
	}

	private List<Roll> generateZeroRollsSpareLastFrame() {
		List<Roll> rolls = new ArrayList<>();
		Roll roll = new Roll("0");
		for (int i = 0; i < 18; i++) {
			rolls.add(roll);
		}
		roll = new Roll("0");
		rolls.add(roll);
		roll = new Roll("10");
		rolls.add(roll);
		roll = new Roll("2");
		rolls.add(roll);

		return rolls;
	}

}
