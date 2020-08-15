package com.luciano.bowlinggame.validator;

import java.util.List;

import com.luciano.bowlinggame.model.Roll;

public interface GameValidator {

	void validateGameRolls(List<Roll> rools);

	void validateGameMoreFrames(List<Roll> rools, int cursor);

	void validateGameLessFrames(List<Roll> rools, int cursor, int position);

}
