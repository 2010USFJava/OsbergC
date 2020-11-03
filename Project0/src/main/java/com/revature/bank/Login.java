package com.revature.bank;

import java.io.Serializable;

public class Login implements Serializable {
	private static final long serialVersionUID = -3243126139747941216L;
	private int userID;
	private String username;
	private String password;
	private String role;
	
	public Login(int userID, String username, String password, String role) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "Login [userID=" + userID + ", username=" + username + ", password=" + password + ", role=" + role + "]";
	}
	
}
