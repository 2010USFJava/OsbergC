package com.revature.bank;

import com.revature.bank.RoleServices.roleName;
import com.revature.daoImpl.AccountDaoImpl;
import com.revature.daoImpl.LoginDaoImpl;
import com.revature.daoImpl.TransactionDaoImpl;
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
	private LoginDaoImpl ldi;
	private AccountDaoImpl adi;
	private TransactionDaoImpl tdi;
	private Integer userID;
	private String givenName;
	private roleName roleName;

	public Role() {
		super();
		this.roleServices = new RoleServices();
		this.fileManager = new FileManager();
		this.ldi = new LoginDaoImpl();
		this.adi = new AccountDaoImpl();
		this.tdi = new TransactionDaoImpl();
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

	public LoginDaoImpl getLdi() {
		return ldi;
	}

	public AccountDaoImpl getAdi() {
		return adi;
	}

	public TransactionDaoImpl getTdi() {
		return tdi;
	}

	public Integer getUserId() {
		return userID;
	}

	public void setUserId(Integer userID) {
		this.userID = userID;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public roleName getRoleName() {
		return roleName;
	}

	public void setRoleName(roleName roleName) {
		this.roleName = roleName;
	}
}
