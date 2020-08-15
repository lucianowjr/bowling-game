package com.luciano.bowlinggame.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.luciano.bowlinggame.exception.InvalidDataException;
import com.luciano.bowlinggame.exception.InvalidValueException;

class RollValidatorImplTest {

	private static RollValidator rollValidator;

	@BeforeAll
	public static void setUp() {
		rollValidator = new RollValidatorImpl();
	}

	@Test
	public void testValidateRollValue_failValueIsBelow() {
		Assertions.assertThrows(InvalidValueException.class, () -> {
			rollValidator.validateRoll("-5");
		});

	}

	@Test
	public void testValidateRollValue_failValueIsOver() {
		Assertions.assertThrows(InvalidValueException.class, () -> {
			rollValidator.validateRoll("15");
		});
	}

	@Test
	public void testValidateRollValue_failWhenValueIsInvalid() {
		Assertions.assertThrows(InvalidDataException.class, () -> {
			rollValidator.validateRoll("G");
		});
	}

	@Test
	public void testValidateRollValue_ValidValues() {
		for (int i = 0; i <= 10; i++) {
			String num = Integer.toString(i);
			assertEquals(num, rollValidator.validateRoll(num));
		}
		assertEquals("f", rollValidator.validateRoll("f"));
		assertEquals("F", rollValidator.validateRoll("F"));
	}

}
