package com.luciano.bowlinggame.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.luciano.bowlinggame.model.Frame;
import com.luciano.bowlinggame.model.Player;
import com.luciano.bowlinggame.model.Roll;

@Service
public class GameServiceImpl implements GameService {

	private static int MAX_FRAMES = 10;
	private static int MAX_ROLL_SCORE = 10;

	@Override
	public List<Player> createGame(Map<String, List<Roll>> rollsMap) {

		List<Player> players = new ArrayList<>();

		for (String name : rollsMap.keySet()) {
			Player player = new Player(name);
			player.setFrames(createFrames(rollsMap.get(name)));
			players.add(player);
		}

		return players;
	}

	private List<Frame> createFrames(List<Roll> rolls) {

		List<Frame> frames = new ArrayList<>();

		int cursor = 0;
		for (int f = 0; f < MAX_FRAMES; f++) {
			if (isStrike(rolls, cursor)) {
				frames.add(createStrikeFrame(rolls, cursor, f));
				cursor++;

			} else if (isSpare(rolls, cursor)) {
				frames.add(createSpareFrame(rolls, cursor));
				cursor += 2;
			} else {
				frames.add(createSimpleFrame(rolls, cursor));
				cursor += 2;
			}
		}

		return frames;
	}

	private Frame createSimpleFrame(List<Roll> rolls, int cursor) {
		Frame frame = new Frame();
		frame.getRolls().add(rolls.get(cursor));
		frame.getRolls().add(rolls.get(cursor + 1));
		frame.setScore(rolls.get(cursor).getPins() + rolls.get(cursor + 1).getPins());
		frame.setSpare(false);
		frame.setStrike(false);
		return frame;
	}

	private Frame createSpareFrame(List<Roll> rolls, int cursor) {
		Frame frame = createSimpleFrame(rolls, cursor);
		frame.setScore(MAX_ROLL_SCORE + rolls.get(cursor + 2).getPins());
		frame.setSpare(true);
		frame.setStrike(false);
		return frame;
	}

	private Frame createStrikeFrame(List<Roll> rolls, int cursor, int position) {
		Roll firstRoll = rolls.get(cursor);
		Roll secondRoll = rolls.get(cursor + 1);
		Roll thirdRoll = rolls.get(cursor + 2);

		Frame frame = new Frame();
		frame.getRolls().add(firstRoll);
		if (position == MAX_FRAMES - 1) {
			frame.getRolls().add(secondRoll);
			frame.getRolls().add(thirdRoll);
		}
		frame.setScore(MAX_ROLL_SCORE + secondRoll.getPins() + thirdRoll.getPins());
		frame.setSpare(false);
		frame.setStrike(true);
		return frame;
	}

	private boolean isStrike(List<Roll> rolls, int cursor) {
		return rolls.get(cursor).getPins() == MAX_ROLL_SCORE;
	}

	private boolean isSpare(List<Roll> rolls, int cursor) {
		return rolls.get(cursor).getPins() + rolls.get(cursor + 1).getPins() == MAX_ROLL_SCORE;
	}

}
