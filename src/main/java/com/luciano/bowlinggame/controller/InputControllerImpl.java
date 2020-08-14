package com.luciano.bowlinggame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.luciano.bowlinggame.service.InputService;

@Controller
public class InputControllerImpl implements InputController {

	@Autowired
	InputService inputService;

	@Override
	public String getInputFilePath() {
		return inputService.getInput();
	}

}
