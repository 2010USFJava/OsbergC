package com.revature.bank;

import java.io.Serializable;

public class AccountApplication implements Serializable {
	private static final long serialVersionUID = -1497836379492524526L;
	private int userID;
	private String accountType;
	private String applicationStatus;

	public AccountApplication(int userID, String accountType, String applicationStatus) {
		super();
		this.userID = userID;
		this.accountType = accountType;
		this.applicationStatus = applicationStatus;
	}

	public int getUserID() {
		return userID;
	}

	public String getAccountType() {
		return accountType;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	@Override
	public String toString() {
		return "AccountApplication [userID=" + userID + ", accountType=" + accountType + ", applicationStatus="
				+ applicationStatus + "]";
	}

}
