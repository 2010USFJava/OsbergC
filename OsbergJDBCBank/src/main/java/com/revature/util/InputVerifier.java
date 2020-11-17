package com.revature.util;

import java.math.BigDecimal;

import com.revature.exception.InvalidInputException;

/**
 * The InputVerifier class contains the functionality for verifying Integer and
 * BigDecimal input.
 * <p>
 * 
 * @author Christopher Osberg
 *
 */
public class InputVerifier {
	
	/**
	 * The verifyIntegerInput method attempts to parse an Integer from the String sSelection.
	 * <p>
	 * 
	 * @return Integer Returns the Integer if successfully parsed, or -1 if unsuccessful.
	 */
	public static Integer verifyIntegerInput(String sSelection, Integer low, Integer high) {
		Integer iSelection = -1;
			iSelection = Integer.parseInt(sSelection);
			if (iSelection < low || iSelection > high) {
				throw new InvalidInputException("Exception: Invalid input");
			}
		return iSelection;
	}

	/**
	 * The verifyBigDecimalInput method attempts to parse an BigDecimal from the String sSelection.
	 * <p>
	 * 
	 * @return BigDecimal Returns the BigDecimal if successfully parsed, or -1 if unsuccessful.
	 */
	public static BigDecimal verifyBigDecimalInput(String sSelection, BigDecimal low, BigDecimal high) {
		BigDecimal selection;
			selection = new BigDecimal(sSelection);
			if (selection.compareTo(low) + selection.compareTo(high) != 0) {
				throw new InvalidInputException("Exception: Invalid input");
			}
		return selection;
	}
}
