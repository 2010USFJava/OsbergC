package com.revature.bank;

import java.io.Serializable;

import com.revature.role.roleName;

/**
 * The Login class is a substitute for a database entry. Its private fields
 * represent columns in the database row.
 * <p>
 * 
 * @author Christopher Osberg
 * 
 */
public class Login implements Serializable {
	private static final long serialVersionUID = -3243126139747941216L;
	private Integer userId;
	private String username;
	private String password;
	private roleName role;
	private String givenName;
	
	public Login(Integer userId, String username, String password, roleName employee, String givenName) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.role = employee;
		this.givenName = givenName;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public roleName getRole() {
		return role;
	}

	public String getGivenName() {
		return givenName;
	}

	@Override
	public String toString() {
		return "Login [userId=" + userId + ", username=" + username + ", password=" + password + ", role=" + role + ", givenName=" + givenName + "]";
	}
	
}
