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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ginsberg.junit.exit.ExpectSystemExit;
import com.luciano.bowlinggame.exception.InvalidGameException;
import com.luciano.bowlinggame.model.FileData;
import com.luciano.bowlinggame.model.Frame;
import com.luciano.bowlinggame.model.Roll;
import com.luciano.bowlinggame.model.Scoreboard;

class GameServiceImplTest {

	@InjectMocks
	private GameServiceImpl gameService;

	@Mock
	private ScoreService scoreService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		List<Roll> rolls = generateRolls();
		List<Frame> frames = new ArrayList<>();
		Mockito.when(scoreService.calculateScore(rolls)).thenReturn(frames);

		List<Roll> emptyRolls = new ArrayList<>();

		InvalidGameException ige = new InvalidGameException("InvalidGameException (Expected in the unit test)");
		Mockito.when(scoreService.calculateScore(emptyRolls)).thenThrow(ige);
	}

	@Test
	public void testCreateScoreboard() {
		List<Roll> rolls = generateRolls();
		Map<String, List<Roll>> rollsMap = new HashMap<>();
		rollsMap.put("Jeff", rolls);
		FileData fileData = new FileData(rollsMap);

		Scoreboard scoreboard = gameService.createScoreboard(fileData);
		assertEquals("Jeff", scoreboard.getPlayers().get(0).getName());
	}

	@Test
	@ExpectSystemExit
	void testCreateScoreboard_EmptyRolls_throwsInvalidGameException() {
		List<Roll> rolls = new ArrayList<>();
		Map<String, List<Roll>> rollsMap = new HashMap<>();
		rollsMap.put("Jeff", rolls);
		FileData fileData = new FileData(rollsMap);
		gameService.createScoreboard(fileData);
	}

	private List<Roll> generateRolls() {
		List<Roll> rolls = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			Roll roll = new Roll(Integer.toString(i));
			rolls.add(roll);
		}
		return rolls;
	}

}
