package com.luciano.bowlinggame.service;

import java.util.List;

import com.luciano.bowlinggame.model.Frame;
import com.luciano.bowlinggame.model.Roll;

public interface ScoreService {

	/**
	 * Returns the calculated score in a list of frames
	 *
	 * @param a roll list
	 * @return a frame list
	 */
	List<Frame> calculateScore(List<Roll> rolls);

}
