package com.revature.bank;

public class Role {
	private RoleServices roleServices;
	private FileManager fileManager;
	private int userID;
	private String givenName;
	private int[] accountNumbers;
	
	public Role() {
		super();
		this.roleServices = new RoleServices();
		this.fileManager = new FileManager();
	}

	public RoleServices getRoleServices() {
		return roleServices;
	}

	public void setRoleServices(RoleServices roleServices) {
		this.roleServices = roleServices;
	}

	public FileManager getFileManager() {
		return fileManager;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public int[] getAccountNumbers() {
		return accountNumbers;
	}

	public void setAccountNumbers(int[] accountNumbers) {
		this.accountNumbers = accountNumbers;
	}
	
}
