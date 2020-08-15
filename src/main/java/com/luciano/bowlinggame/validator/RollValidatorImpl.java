package com.luciano.bowlinggame.validator;

import org.springframework.stereotype.Component;

import com.luciano.bowlinggame.exception.InvalidDataException;
import com.luciano.bowlinggame.exception.InvalidValueException;

@Component
public class RollValidatorImpl implements RollValidator {

	private static final String FOUL_SIGN = "f";

	@Override
	public String validateRoll(String value) {
		try {
			int intValue = Integer.parseInt(value);
			if (intValue < 0 || intValue > 10) {
				throw new InvalidValueException("The roll value '" + value
						+ "' is outside the range 0 to 10.  Please, check the input file format.");
			}
		} catch (NumberFormatException e) {
			if (!FOUL_SIGN.equalsIgnoreCase(value)) {
				throw new InvalidDataException(
						"The roll value '" + value + "' is invalid. Please, check the input file format.");
			}
		}
		return value;
	}

}
