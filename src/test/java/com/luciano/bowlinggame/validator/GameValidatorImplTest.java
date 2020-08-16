package com.luciano.bowlinggame.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.luciano.bowlinggame.exception.InvalidGameException;
import com.luciano.bowlinggame.model.Roll;

class GameValidatorImplTest {

	private GameValidator gameValidator;

	private static String LESS_FRAMES_MESSAGE = "There were less than 10 frames in a player game. Please, check the input file.";
	private static String MORE_FRAMES_MESSAGE = "There were more than 10 frames in a player game. Please, check the input file.";

	@BeforeEach
	public void setUp() {
		gameValidator = new GameValidatorImpl();
	}

	@Test
	public void testValidateGameRolls_failRollsBelow() {
		Exception e = Assertions.assertThrows(InvalidGameException.class, () -> {

			List<Roll> rolls = new ArrayList<>();
			for (int i = 0; i < 10; i++) {
				Roll roll = new Roll(Integer.toString(i));
				rolls.add(roll);
			}

			gameValidator.validateGameRolls(rolls);
		});
		assertEquals(LESS_FRAMES_MESSAGE, e.getMessage());
	}

	@Test
	public void testValidateGameRolls_failRollsOver() {
		Exception e = Assertions.assertThrows(InvalidGameException.class, () -> {

			List<Roll> rolls = new ArrayList<>();
			for (int i = 0; i < 25; i++) {
				Roll roll = new Roll(Integer.toString(i));
				rolls.add(roll);
			}

			gameValidator.validateGameRolls(rolls);
		});
		assertEquals(MORE_FRAMES_MESSAGE, e.getMessage());
	}

	@Test
	public void testValidateGameMoreFrames_failRollsOver() {
		Exception e = Assertions.assertThrows(InvalidGameException.class, () -> {

			int cursor = 23;
			List<Roll> rolls = new ArrayList<>();
			for (int i = 0; i < 25; i++) {
				Roll roll = new Roll(Integer.toString(i));
				rolls.add(roll);
			}

			gameValidator.validateGameMoreFrames(rolls, cursor);
		});
		assertEquals(MORE_FRAMES_MESSAGE, e.getMessage());
	}

	@Test
	public void testValidateGameLessFrames_failRollsBelow() {
		Exception e = Assertions.assertThrows(InvalidGameException.class, () -> {

			int cursor = 25;
			int position = 8;
			List<Roll> rolls = new ArrayList<>();
			for (int i = 0; i < 25; i++) {
				Roll roll = new Roll(Integer.toString(i));
				rolls.add(roll);
			}

			gameValidator.validateGameLessFrames(rolls, cursor, position);
		});
		assertEquals(LESS_FRAMES_MESSAGE, e.getMessage());
	}

}
