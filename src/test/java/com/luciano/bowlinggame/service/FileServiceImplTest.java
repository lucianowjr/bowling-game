package com.luciano.bowlinggame.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.luciano.bowlinggame.model.Roll;
import com.luciano.bowlinggame.validator.RollValidator;

class FileServiceImplTest {

	private static String SAMPLE_FILE = "src/test/resources/sample.txt";
	private static String PERFECT_FILE = "src/test/resources/perfect.txt";
	private static String ZERO_FILE = "src/test/resources/zero.txt";

	@InjectMocks
	private FileServiceImpl fileService;

	@Mock
	private RollValidator rollValidator;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		for (int i = 0; i <= 10; i++) {
			Mockito.when(rollValidator.validateRoll(Integer.toString(i))).thenReturn(Integer.toString(i));
		}
		Mockito.when(rollValidator.validateRoll("F")).thenReturn("F");
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

		Map<String, List<Roll>> rollMap = fileService.readFile(SAMPLE_FILE);

		assertTrue(rollMap.containsKey("Jeff"));
		assertTrue(rollMap.containsKey("John"));

		assertTrue(rollMap.get("Jeff").containsAll(firstPlayer));
		assertTrue(rollMap.get("John").containsAll(secondPlayer));
	}

	@Test
	void testReadFile_Perfect() {
		List<Roll> player = new ArrayList<>();

		for (int i = 0; i < 12; i++) {
			player.add(new Roll("10"));
		}

		Map<String, List<Roll>> rollMap = fileService.readFile(PERFECT_FILE);

		assertTrue(rollMap.containsKey("Carl"));
		assertTrue(rollMap.get("Carl").containsAll(player));
	}

	@Test
	void testReadFile_Zero() {
		List<Roll> player = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			player.add(new Roll("0"));
		}

		Map<String, List<Roll>> rollMap = fileService.readFile(ZERO_FILE);

		assertTrue(rollMap.containsKey("Hugo"));
		assertTrue(rollMap.get("Hugo").containsAll(player));
	}

}
