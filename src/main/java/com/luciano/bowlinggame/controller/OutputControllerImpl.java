package com.luciano.bowlinggame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.luciano.bowlinggame.model.Player;
import com.luciano.bowlinggame.service.OutputService;
import com.luciano.bowlinggame.service.OutputServiceImpl;
import com.luciano.bowlinggame.view.OutputView;

@Controller
public class OutputControllerImpl implements OutputController {

	@Autowired
	OutputService outputService;

	@Autowired
	OutputView outputView;

	public OutputControllerImpl() {
		super();
		if (outputService == null) {
			outputService = new OutputServiceImpl();
		}
	}

	@Override
	public void printScoreBoard(List<Player> players) {
		outputView.print(outputService.getScoreBoard(players));
	}

	@Override
	public String getScoreBoard(List<Player> players) {
		return outputService.getScoreBoard(players);
	}

}
