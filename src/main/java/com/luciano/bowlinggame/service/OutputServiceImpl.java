package com.luciano.bowlinggame.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.luciano.bowlinggame.model.Frame;
import com.luciano.bowlinggame.model.Player;

@Service
public class OutputServiceImpl implements OutputService {

	private static String STRIKE_MARK = "X";
	private static String SPARE_MARK = "/";
	private static int FIRST_ROLL = 0;
	private static int SECOND_ROLL = 1;
	private static int THIRD_ROLL = 2;
	private static int MAX_ROLL_SCORE = 10;
	private int score = 0;

	@Override
	public String printScoreBoard(List<Player> players) {
		StringBuilder scoreboard = new StringBuilder();
		scoreboard.append(printHeader());

		players.stream().forEach(player -> {
			scoreboard.append(player.getName() + "\n");
			scoreboard.append("Pinfalls");
			player.getFrames().stream().forEach(frame -> {
				if (frame.isStrike()) {
					if (frame.getRolls().size() == 3) {
						scoreboard.append(printStrikeLastFrame(frame));
					} else {
						scoreboard.append(printStrike(frame));
					}
				} else if (frame.isSpare()) {
					scoreboard.append(printSpareFrame(frame));

				} else {
					scoreboard.append(printSimpleFrame(frame));
				}
			});
			scoreboard.append(printScore(player));
		});
		return scoreboard.toString();
	}

	private String printHeader() {
		return "\n\nFrame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n";
	}

	private String printScore(Player player) {
		StringBuilder score = new StringBuilder("\nScore");
		player.getFrames().stream().forEach(frame -> {
			incrementScore(frame.getScore());
			score.append("\t\t" + getScore());
		});
		setScore(0);
		return score.append("\n").toString();
	}

	private String printStrikeLastFrame(Frame frame) {
		StringBuilder strike = new StringBuilder("\t" + STRIKE_MARK);

		if (frame.getRolls().get(SECOND_ROLL).getPins() == MAX_ROLL_SCORE) {
			strike.append("\t" + STRIKE_MARK);
		} else
			strike.append("\t" + frame.getRolls().get(SECOND_ROLL).getValue());

		if (frame.getRolls().get(THIRD_ROLL).getPins() == MAX_ROLL_SCORE) {
			strike.append("\t" + STRIKE_MARK);
		} else
			strike.append("\t" + frame.getRolls().get(THIRD_ROLL).getValue());

		return strike.toString();
	}

	private String printStrike(Frame frame) {
		return "\t\t" + STRIKE_MARK;
	}

	private String printSimpleFrame(Frame frame) {
		return "\t" + frame.getRolls().get(FIRST_ROLL).getValue() + "\t" + frame.getRolls().get(SECOND_ROLL).getValue();
	}

	private String printSpareFrame(Frame frame) {

		return "\t" + frame.getRolls().get(FIRST_ROLL).getValue() + "\t" + SPARE_MARK;
	}

	private void incrementScore(int score) {
		this.score += score;
	}

	private int getScore() {
		return this.score;
	}

	private void setScore(int score) {
		this.score = score;
	}

}
