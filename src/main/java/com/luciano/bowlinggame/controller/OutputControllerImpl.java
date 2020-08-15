package com.luciano.bowlinggame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.luciano.bowlinggame.model.Player;
import com.luciano.bowlinggame.service.OutputService;
import com.luciano.bowlinggame.view.OutputView;

@Controller
public class OutputControllerImpl implements OutputController {

	@Autowired
	OutputService outputService;

	@Autowired
	OutputView outputView;

	@Override
	public void printScoreBoard(List<Player> players) {
		String scoreboard = outputService.getScoreBoard(players);
		outputView.print(scoreboard);
	}

}
