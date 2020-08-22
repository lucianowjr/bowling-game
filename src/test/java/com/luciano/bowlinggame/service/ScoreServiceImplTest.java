package com.luciano.bowlinggame.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.luciano.bowlinggame.model.Frame;
import com.luciano.bowlinggame.model.Roll;
import com.luciano.bowlinggame.validator.ScoreValidator;

class ScoreServiceImplTest {

	private static int MAX_SCORE_FRAME = 30;
	private static int MIN_SCORE_FRAME = 0;

	@InjectMocks
	private ScoreServiceImpl scoreService;

	@Mock
	private ScoreValidator gameValidator;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testCalculateScoreSampleGame() {
		List<Roll> rolls = generateSampleRolls();
		List<Frame> frames = scoreService.calculateScore(rolls);

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
	void testCalculateScorePerfectGame() {
		List<Roll> rolls = generatePerfectRolls();
		List<Frame> frames = scoreService.calculateScore(rolls);

		for (Frame frame : frames) {
			assertEquals(frame.getScore(), MAX_SCORE_FRAME);
		}
	}

	@Test
	void testCalculateScoreZeroGame() {
		List<Roll> rolls = generateZeroRolls();
		List<Frame> frames = scoreService.calculateScore(rolls);
		for (Frame frame : frames) {
			assertEquals(frame.getScore(), MIN_SCORE_FRAME);
		}
	}

	@Test
	void testCalculateScoreZeroGameSpareLastFrame() {
		List<Roll> rolls = generateZeroRollsSpareLastFrame();
		List<Frame> frames = scoreService.calculateScore(rolls);
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
