package com.luciano.bowlinggame.model;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {

	List<Player> players;

	public Scoreboard() {
		super();
		this.players = new ArrayList<>();
	}

	public Scoreboard(List<Player> players) {
		super();
		this.players = players;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

}
