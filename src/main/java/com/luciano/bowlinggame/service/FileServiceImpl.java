package com.luciano.bowlinggame.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.luciano.bowlinggame.exception.FileParsingException;
import com.luciano.bowlinggame.model.Roll;

@Service
public class FileServiceImpl implements FileService {

	private static String REGEX = "\t";
	private static int PLAYER = 0;
	private static int PIN = 1;

	public Map<String, List<Roll>> readFile(String path) {

		Map<String, List<Roll>> mapRolls = new HashMap<>();

		try (Stream<String> stream = Files.lines(Paths.get(path))) {

			stream.forEach(line -> {
				String[] row = line.split(REGEX);
				List<Roll> rolls = mapRolls.getOrDefault(row[PLAYER], new ArrayList<Roll>());
				rolls.add(new Roll(row[PIN]));
				mapRolls.put(row[PLAYER], rolls);
			});
		} catch (IOException e) {
			throw new FileParsingException("An error occurred when reading the file. Please, check the path.");
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new FileParsingException("An error occurred when reading the file. Please, check the file format.");
		}

		return mapRolls;
	}
}
