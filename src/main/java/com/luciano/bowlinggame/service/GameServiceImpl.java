package com.luciano.bowlinggame.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luciano.bowlinggame.exception.InvalidGameException;
import com.luciano.bowlinggame.model.Frame;
import com.luciano.bowlinggame.model.Player;
import com.luciano.bowlinggame.model.Roll;
import com.luciano.bowlinggame.validator.GameValidator;
import com.luciano.bowlinggame.validator.GameValidatorImpl;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private GameValidator gameValidator;

	private static int MAX_FRAMES = 10;
	private static int MAX_ROLL_SCORE = 10;

	public GameServiceImpl() {
		super();

		if (gameValidator == null) {
			gameValidator = new GameValidatorImpl();
		}
	}

	@Override
	public List<Player> createGame(Map<String, List<Roll>> rollsMap) {
		List<Player> players = new ArrayList<>();
		try {
			for (String name : rollsMap.keySet()) {
				Player player = new Player(name);
				player.setFrames(createFrames(rollsMap.get(name)));
				players.add(player);
			}
		} catch (InvalidGameException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		return players;
	}

	private List<Frame> createFrames(List<Roll> rolls) {
		gameValidator.validateGameRolls(rolls);

		List<Frame> frames = new ArrayList<>();

		int cursor = 0;
		for (int p = 0; p < MAX_FRAMES; p++) {
			if (isStrike(rolls, cursor)) {
				frames.add(createStrikeFrame(rolls, cursor, p));
				if (p == MAX_FRAMES - 1) {
					cursor += 2;
				} else {
					cursor++;
				}
			} else if (isSpare(rolls, cursor)) {
				frames.add(createSpareFrame(rolls, cursor, p));
				cursor += 2;
			} else {
				frames.add(createSimpleFrame(rolls, cursor));
				cursor += 2;
			}
			gameValidator.validateGameLessFrames(rolls, cursor, p);
		}

		gameValidator.validateGameMoreFrames(rolls, cursor);

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

	private Frame createSpareFrame(List<Roll> rolls, int cursor, int position) {
		Frame frame = createSimpleFrame(rolls, cursor);
		if (position == MAX_FRAMES - 1) {
			frame.getRolls().add(rolls.get(cursor + 2));
		}
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
