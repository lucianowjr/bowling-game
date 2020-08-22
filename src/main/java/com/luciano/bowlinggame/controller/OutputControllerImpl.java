package com.luciano.bowlinggame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.luciano.bowlinggame.model.Scoreboard;
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
	public void printScoreboard(Scoreboard scoreboard) {
		outputView.print(outputService.getScoreboard(scoreboard));
	}

	@Override
	public String getScoreboard(Scoreboard scoreboard) {
		return outputService.getScoreboard(scoreboard);
	}

}
