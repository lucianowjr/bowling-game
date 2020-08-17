package com.luciano.bowlinggame.validator;

import java.util.List;

import com.luciano.bowlinggame.model.Roll;

public interface GameValidator {

	/**
	 * Validate the list of rolls of a game
	 *
	 * @param list of rolls
	 */
	void validateGameRolls(List<Roll> rools);

	/**
	 * Validate the list of rolls checking if this game have more than ten frames
	 *
	 * @param list of rolls and a control cursor
	 */
	void validateGameMoreFrames(List<Roll> rools, int cursor);

	/**
	 * Validate the list of rolls checking if this game have less than ten frames
	 *
	 * @param list of rolls, a control cursor and the frame position
	 */
	void validateGameLessFrames(List<Roll> rools, int cursor, int position);

}
