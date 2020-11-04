package com.revature.bank;

import java.io.Serializable;
import java.math.BigDecimal;

public class Account implements Serializable {
	private static final long serialVersionUID = -2189496940240975358L;
	private int number;
	private int userID;
	private BigDecimal balance;

	public Account(int number, int userID, BigDecimal balance) {
		super();
		this.number = number;
		this.userID = userID;
		this.balance = balance;
	}

	public int getNumber() {
		return number;
	}

	public int getUserID() {
		return userID;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	@Override
	public String toString() {
		return "Account [number=" + number + ", userID=" + userID + ", balance=" + balance + "]";
	}

}
