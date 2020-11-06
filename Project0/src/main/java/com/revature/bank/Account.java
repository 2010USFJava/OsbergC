package com.revature.bank;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Account implements Serializable {
	private static final long serialVersionUID = -2189496940240975358L;
	private Integer accountNumber;
	private String accountType;
	private ArrayList<Integer> userIDs;
	private BigDecimal balance;

	public Account(Integer accountNumber, String accountType, ArrayList<Integer> userIDs, BigDecimal balance) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.userIDs = userIDs;
		this.balance = balance;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public ArrayList<Integer> getUserIDs() {
		return userIDs;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public String getAccountType() {
		return accountType;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", accountType=" + accountType + ", userIDs=" + userIDs
				+ ", balance=" + balance + "]";
	}

}
