package com.luciano.bowlinggame.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luciano.bowlinggame.exception.FileParsingException;
import com.luciano.bowlinggame.exception.InvalidDataException;
import com.luciano.bowlinggame.model.Roll;
import com.luciano.bowlinggame.validator.RollValidator;
import com.luciano.bowlinggame.validator.RollValidatorImpl;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private RollValidator rollValidator;

	private static final String TAB = "\t";
	private static final int PLAYER = 0;
	private static final int PIN = 1;

	Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

	public FileServiceImpl() {
		super();

		if (rollValidator == null) {
			rollValidator = new RollValidatorImpl();
		}
	}

	public Map<String, List<Roll>> readFile(String path) {

		Map<String, List<Roll>> mapRolls = new HashMap<>();

		try (Stream<String> stream = Files.lines(Paths.get(path))) {

			stream.forEach(line -> {
				String[] row = line.split(TAB);
				List<Roll> rolls = mapRolls.getOrDefault(row[PLAYER], new ArrayList<>());
				String value = rollValidator.validateRoll(row[PIN]);
				rolls.add(new Roll(value));
				mapRolls.put(row[PLAYER], rolls);
			});
		} catch (InvalidDataException e) {
			logger.error(e.getMessage());
			System.exit(0);
		} catch (IOException e) {
			throw new FileParsingException("An error occurred when reading the file. Please, check the path.");
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new FileParsingException("An error occurred when reading the file. Please, check the file format.");
		}

		return mapRolls;
	}
}
