package com.revature.bank;

import java.io.Serializable;
import java.util.ArrayList;

public class AccountApplication implements Serializable {
	private static final long serialVersionUID = -1497836379492524526L;
	private ArrayList<Integer> userIDs;
	private String accountType;
	private Integer accountNumber;

	public AccountApplication(ArrayList<Integer> userIDs, String accountType, Integer accountNumber) {
		super();
		this.userIDs = userIDs;
		this.accountType = accountType;
		this.accountNumber = accountNumber;
	}

	public ArrayList<Integer> getUserIDs() {
		return userIDs;
	}

	public String getAccountType() {
		return accountType;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	@Override
	public String toString() {
		return "AccountApplication [userIDs=" + userIDs + ", accountType=" + accountType + ", accountNumber="
				+ accountNumber + "]";
	}

	
}
