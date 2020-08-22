package com.luciano.bowlinggame.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.luciano.bowlinggame.exception.FileParsingException;
import com.luciano.bowlinggame.model.FileData;
import com.luciano.bowlinggame.model.Roll;
import com.luciano.bowlinggame.service.InputService;
import com.luciano.bowlinggame.service.InputServiceImpl;

@Controller
public class InputControllerImpl implements InputController {

	@Autowired
	InputService inputService;

	Logger logger = LoggerFactory.getLogger(InputControllerImpl.class);

	public InputControllerImpl() {
		super();
		if (inputService == null) {
			inputService = new InputServiceImpl();
		}
	}

	@Override
	public FileData getFileData(String filePath) {
		FileData fileData = null;
		try {
			Map<String, List<Roll>> rollsMap = inputService.readFile(filePath);
			fileData = new FileData(rollsMap);
		} catch (FileParsingException e) {
			logger.error(e.getMessage());
			System.exit(0);
		}
		return fileData;
	}

}
