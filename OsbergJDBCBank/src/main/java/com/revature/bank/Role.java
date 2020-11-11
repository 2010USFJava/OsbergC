package com.revature.bank;

import java.util.ArrayList;
import com.revature.bank.RoleServices.roleName;
import com.revature.util.FileManager;

/**
 * The Role class represents the user and their information. It also contains
 * references to non-package classes used for the various utilities.
 * <p>
 * 
 * @author Christopher Osberg
 *
 */
public class Role {
	private RoleServices roleServices;
	private FileManager fileManager;
	private Integer userID;
	private String givenName;
	private ArrayList<Integer> accountNumbers;
	private roleName roleName;

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

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public ArrayList<Integer> getAccountNumbers() {
		return accountNumbers;
	}

	public void setAccountNumbers(ArrayList<Integer> accountNumbers) {
		this.accountNumbers = accountNumbers;
	}

	public roleName getRoleName() {
		return roleName;
	}

	public void setRoleName(roleName roleName) {
		this.roleName = roleName;
	}

}
