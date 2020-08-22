package com.luciano.bowlinggame.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.luciano.bowlinggame.exception.InvalidDataException;
import com.luciano.bowlinggame.exception.InvalidValueException;

class RollValidatorImplTest {

	private RollValidator rollValidator;

	@BeforeEach
	public void setUp() {
		rollValidator = new RollValidatorImpl();
	}

	@Test
	void testValidateRollValue_failValueIsBelow() {
		InvalidValueException thrown = Assertions.assertThrows(InvalidValueException.class, () -> {
			rollValidator.validateRoll("-5");
		});
		assertTrue(thrown.getMessage().contains("outside the range 0 to 10"));
	}

	@Test
	void testValidateRollValue_failValueIsOver() {
		InvalidValueException thrown = Assertions.assertThrows(InvalidValueException.class, () -> {
			rollValidator.validateRoll("15");
		});
		assertTrue(thrown.getMessage().contains("outside the range 0 to 10"));
	}

	@Test
	void testValidateRollValue_failWhenValueIsInvalid() {
		InvalidDataException thrown = Assertions.assertThrows(InvalidDataException.class, () -> {
			rollValidator.validateRoll("G");
		});
		assertTrue(thrown.getMessage().contains("is invalid"));
	}

	@Test
	void testValidateRollValue_ValidValues() {
		for (int i = 0; i <= 10; i++) {
			String num = Integer.toString(i);
			assertEquals(num, rollValidator.validateRoll(num));
		}
		assertEquals("f", rollValidator.validateRoll("f"));
		assertEquals("F", rollValidator.validateRoll("F"));
	}

}
