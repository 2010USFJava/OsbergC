package com.revature.util;

import java.math.BigDecimal;

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
		try {
			iSelection = Integer.parseInt(sSelection);
			if (iSelection < low || iSelection > high) {
				System.out.println("Error: Invalid input.");
				return -1;
			}
		} catch (Exception e) {
			System.out.println("Error: Invalid input");
			return -1;
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
		try {
			selection = new BigDecimal(sSelection);
			if (selection.compareTo(low) + selection.compareTo(high) != 0) {
				System.out.println("Error: Invalid input.");
				return BigDecimal.valueOf(-1);
			}
		} catch (Exception e) {
			System.out.println("Error: Invalid input");
			return BigDecimal.valueOf(-1);
		}
		return selection;
	}
}
