package com.luciano.bowlinggame.service;

import org.springframework.stereotype.Service;

import com.luciano.bowlinggame.model.Frame;
import com.luciano.bowlinggame.model.Player;
import com.luciano.bowlinggame.model.Scoreboard;

@Service
public class OutputServiceImpl implements OutputService {

	private static String STRIKE_MARK = "X";
	private static String SPARE_MARK = "/";
	private static int FIRST_ROLL = 0;
	private static int SECOND_ROLL = 1;
	private static int THIRD_ROLL = 2;
	private static int MAX_FRAME_SIZE = 3;
	private static int MAX_ROLL_SCORE = 10;
	private static String HEADER = "\nFrame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n";

	private int score = 0;

	@Override
	public String getScoreboard(Scoreboard scoreboard) {
		StringBuilder sb = new StringBuilder();

		sb.append(HEADER);

		scoreboard.getPlayers().stream().forEach(player -> {
			sb.append(player.getName() + "\n");
			sb.append("Pinfalls");

			player.getFrames().stream().forEach(frame -> {
				if (frame.isStrike()) {
					sb.append(getStrikeFrame(frame));
				} else if (frame.isSpare()) {
					sb.append(getSpareFrame(frame));
				} else {
					sb.append(getSimpleFrame(frame));
				}
			});

			sb.append(getScore(player));
		});

		return sb.toString();
	}

	private String getSimpleFrame(Frame frame) {
		return "\t" + frame.getRolls().get(FIRST_ROLL).getValue() + "\t" + frame.getRolls().get(SECOND_ROLL).getValue();
	}

	private String getStrikeFrame(Frame frame) {
		StringBuilder strike = new StringBuilder();

		if (frame.getRolls().size() < MAX_FRAME_SIZE) {
			strike.append("\t\t" + STRIKE_MARK);
		} else {
			strike.append("\t" + STRIKE_MARK);
			if (frame.getRolls().get(SECOND_ROLL).getPins() == MAX_ROLL_SCORE) {
				strike.append("\t" + STRIKE_MARK);
			} else {
				strike.append("\t" + frame.getRolls().get(SECOND_ROLL).getValue());
			}
			if (frame.getRolls().get(THIRD_ROLL).getPins() == MAX_ROLL_SCORE) {
				strike.append("\t" + STRIKE_MARK);
			} else {
				strike.append("\t" + frame.getRolls().get(THIRD_ROLL).getValue());
			}
		}
		return strike.toString();
	}

	private String getSpareFrame(Frame frame) {
		StringBuilder spare = new StringBuilder("\t");
		spare.append(frame.getRolls().get(FIRST_ROLL).getValue() + "\t" + SPARE_MARK);
		if (frame.getRolls().size() == MAX_FRAME_SIZE) {
			spare.append("\t" + frame.getRolls().get(THIRD_ROLL).getValue());
		}
		return spare.toString();
	}

	private String getScore(Player player) {
		StringBuilder stringBuilder = new StringBuilder("\nScore");
		player.getFrames().stream().forEach(frame -> {
			incrementScore(frame.getScore());
			stringBuilder.append("\t\t" + getScore());
		});
		setScore(0);
		return stringBuilder.append("\n").toString();
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
