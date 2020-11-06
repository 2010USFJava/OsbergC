package com.revature.bank;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Account implements Serializable {
	private static final long serialVersionUID = -2189496940240975358L;
	private int accountNumber;
	private ArrayList<Integer> userIDs;
	private BigDecimal balance;

	public Account(int accountNumber, ArrayList<Integer> userIDs, BigDecimal balance) {
		super();
		this.accountNumber = accountNumber;
		this.userIDs = userIDs;
		this.balance = balance;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public ArrayList<Integer> getUserIDs() {
		return userIDs;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", userIDs=" + userIDs + ", balance=" + balance + "]";
	}

}
