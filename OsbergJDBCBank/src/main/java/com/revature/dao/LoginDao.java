package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.bank.Login;

public interface LoginDao {

	public List<Login> getAllLogins() throws SQLException;

	public void insertLogin(Login login) throws SQLException;

	public Login getLoginByUserId(Integer iUserId) throws SQLException;
	
	public void deleteUser(Integer iUserId) throws SQLException;
}
