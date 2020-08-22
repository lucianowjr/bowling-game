package com.luciano.bowlinggame.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ginsberg.junit.exit.ExpectSystemExit;
import com.luciano.bowlinggame.exception.FileParsingException;
import com.luciano.bowlinggame.exception.InvalidDataException;
import com.luciano.bowlinggame.exception.InvalidValueException;
import com.luciano.bowlinggame.model.Roll;
import com.luciano.bowlinggame.validator.RollValidator;

class InputServiceImplTest {

	private static String SAMPLE_FILE = "src/test/resources/sample.txt";
	private static String PERFECT_FILE = "src/test/resources/perfect.txt";
	private static String ZERO_FILE = "src/test/resources/zero.txt";
	private static String INVALID_DATA_FILE = "src/test/resources/invaliddata.txt";
	private static String INVALID_VALUE_FILE = "src/test/resources/invalidvalue.txt";
	private static String BAD_FORMAT_SAMPLE = "src/test/resources/badformatsample.txt";
	private static String EMPTY_FILE = "src/test/resources/empty.txt";

	@InjectMocks
	private InputServiceImpl inputService;

	@Mock
	private RollValidator rollValidator;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		for (int i = 0; i <= 10; i++) {
			Mockito.when(rollValidator.validateRoll(Integer.toString(i))).thenReturn(Integer.toString(i));
		}
		Mockito.when(rollValidator.validateRoll("F")).thenReturn("F");

		InvalidValueException ive = new InvalidValueException("InvalidValueException (Expected in the unit test)");
		Mockito.when(rollValidator.validateRoll("-1")).thenThrow(ive);

		InvalidDataException ide = new InvalidDataException("InvalidDataException (Expected in the unit test)");
		Mockito.when(rollValidator.validateRoll("G")).thenThrow(ide);
	}

	@Test
	void testReadFile_Sample() {
		List<Roll> firstPlayer = new ArrayList<>();
		List<Roll> secondPlayer = new ArrayList<>();

		firstPlayer.add(new Roll("F"));
		firstPlayer.add(new Roll("7"));
		firstPlayer.add(new Roll("8"));
		firstPlayer.add(new Roll("9"));
		firstPlayer.add(new Roll("10"));

		secondPlayer.add(new Roll("0"));
		secondPlayer.add(new Roll("1"));
		secondPlayer.add(new Roll("3"));
		secondPlayer.add(new Roll("6"));
		secondPlayer.add(new Roll("7"));
		secondPlayer.add(new Roll("8"));
		secondPlayer.add(new Roll("10"));

		Map<String, List<Roll>> rollMap = inputService.readFile(SAMPLE_FILE);

		assertTrue(rollMap.containsKey("Jeff"));
		assertTrue(rollMap.containsKey("John"));

		assertTrue(rollMap.get("Jeff").containsAll(firstPlayer));
		assertTrue(rollMap.get("John").containsAll(secondPlayer));
	}

	@Test
	void testReadFile_Perfect() {
		List<Roll> rolls = new ArrayList<>();

		for (int i = 0; i < 12; i++) {
			rolls.add(new Roll("10"));
		}

		Map<String, List<Roll>> rollMap = inputService.readFile(PERFECT_FILE);

		assertTrue(rollMap.containsKey("Carl"));
		assertTrue(rollMap.get("Carl").containsAll(rolls));
	}

	@Test
	void testReadFile_Zero() {
		List<Roll> rolls = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			rolls.add(new Roll("0"));
		}

		Map<String, List<Roll>> rollMap = inputService.readFile(ZERO_FILE);

		assertTrue(rollMap.containsKey("Hugo"));
		assertTrue(rollMap.get("Hugo").containsAll(rolls));
	}

	@Test
	@ExpectSystemExit
	void testReadFile_Invalid_throwsInvalidValueException() {
		inputService.readFile(INVALID_VALUE_FILE);
	}

	@Test
	@ExpectSystemExit
	void testReadFile_Invalid_throwsInvalidDataException() {
		inputService.readFile(INVALID_DATA_FILE);
	}

	@Test
	void testReadFile_WrongPath_throwsFileParsingException() {
		Assertions.assertThrows(FileParsingException.class, () -> {
			inputService.readFile("/wrong/path");
		});
	}

	@Test
	void testReadFile_BadFormat_throwsFileParsingException() {
		Assertions.assertThrows(FileParsingException.class, () -> {
			inputService.readFile(BAD_FORMAT_SAMPLE);
		});
	}

	@Test
	void testReadFile_Empty_throwsFileParsingException() {
		Assertions.assertThrows(FileParsingException.class, () -> {
			inputService.readFile(EMPTY_FILE);
		});
	}

}
