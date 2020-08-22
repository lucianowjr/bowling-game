package com.luciano.bowlinggame.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.luciano.bowlinggame.exception.InvalidGameException;
import com.luciano.bowlinggame.model.Roll;

class ScoreValidatorImplTest {

	private ScoreValidator gameValidator;

	private static String LESS_FRAMES_MESSAGE = "There were less than 10 frames in a player game. Please, check the input file.";
	private static String MORE_FRAMES_MESSAGE = "There were more than 10 frames in a player game. Please, check the input file.";

	@BeforeEach
	public void setUp() {
		gameValidator = new ScoreValidatorImpl();
	}

	@Test
	void testValidateGameRolls_failRollsBelow() {
		List<Roll> rolls = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			rolls.add(new Roll(Integer.toString(i)));
		}
		Exception e = Assertions.assertThrows(InvalidGameException.class, () -> {
			gameValidator.validateGameRolls(rolls);
		});
		assertEquals(LESS_FRAMES_MESSAGE, e.getMessage());
	}

	@Test
	void testValidateGameRolls_failRollsOver() {
		List<Roll> rolls = new ArrayList<>();
		for (int i = 0; i < 25; i++) {
			rolls.add(new Roll(Integer.toString(i)));
		}
		Exception e = Assertions.assertThrows(InvalidGameException.class, () -> {
			gameValidator.validateGameRolls(rolls);
		});
		assertEquals(MORE_FRAMES_MESSAGE, e.getMessage());
	}

	@Test
	void testValidateGameMoreFrames_failRollsOver() {
		int cursor = 23;
		List<Roll> rolls = new ArrayList<>();
		for (int i = 0; i < 25; i++) {
			rolls.add(new Roll(Integer.toString(i)));
		}
		Exception e = Assertions.assertThrows(InvalidGameException.class, () -> {
			gameValidator.validateGameMoreFrames(rolls, cursor);
		});
		assertEquals(MORE_FRAMES_MESSAGE, e.getMessage());
	}

	@Test
	void testValidateGameLessFrames_failRollsBelow() {
		List<Roll> rolls = new ArrayList<>();
		int cursor = 25;
		int position = 8;
		for (int i = 0; i < 25; i++) {
			rolls.add(new Roll(Integer.toString(i)));
		}
		Exception e = Assertions.assertThrows(InvalidGameException.class, () -> {
			gameValidator.validateGameLessFrames(rolls, cursor, position);
		});
		assertEquals(LESS_FRAMES_MESSAGE, e.getMessage());
	}

}
