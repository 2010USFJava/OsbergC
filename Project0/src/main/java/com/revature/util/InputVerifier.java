package com.revature.util;

import java.math.BigDecimal;

public class InputVerifier {
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
