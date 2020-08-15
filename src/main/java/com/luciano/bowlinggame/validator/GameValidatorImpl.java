package com.luciano.bowlinggame.validator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.luciano.bowlinggame.exception.InvalidGameException;
import com.luciano.bowlinggame.model.Roll;

@Component
public class GameValidatorImpl implements GameValidator {

	private static int MIN_ROLLS_GAME = 11;
	private static int MAX_ROLLS_GAME = 21;
	private static int MAX_FRAMES = 10;
	private static String LESS_FRAMES_MESSAGE = "There were less than 10 frames in a player game. Please, check the input file.";
	private static String MORE_FRAMES_MESSAGE = "There were more than 10 frames in a player game. Please, check the input file.";

	@Override
	public void validateGameRolls(List<Roll> rools) {
		if (rools.size() < MIN_ROLLS_GAME) {
			throw new InvalidGameException(LESS_FRAMES_MESSAGE);
		}

		if (rools.size() > MAX_ROLLS_GAME) {
			throw new InvalidGameException(MORE_FRAMES_MESSAGE);
		}
	}

	@Override
	public void validateGameMoreFrames(List<Roll> rools, int cursor) {
		if (rools.size() - 1 > cursor) {
			throw new InvalidGameException(MORE_FRAMES_MESSAGE);
		}
	}

	@Override
	public void validateGameLessFrames(List<Roll> rools, int cursor, int position) {
		if (rools.size() - 1 <= cursor && position < MAX_FRAMES - 1) {
			throw new InvalidGameException(LESS_FRAMES_MESSAGE);
		}
	}
}
