package com.revature.bank;

import java.sql.Timestamp;

public class Transaction {
	private Timestamp timestamp;
	private Integer iUserId;
	private String sMessage;
	
	public Transaction() {
		super();
	}
	
	public Transaction(Timestamp date, Integer iUserId, String message) {
		super();
		this.timestamp = date;
		this.iUserId = iUserId;
		this.sMessage = message;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	public Integer getiUserId() {
		return iUserId;
	}
	
	public String getsMessage() {
		return sMessage;
	}
	
	@Override
	public String toString() {
		return "Transaction [timestamp=" + timestamp + ", iUserId=" + iUserId + ", sMessage=" + sMessage + "]";
	}
}
