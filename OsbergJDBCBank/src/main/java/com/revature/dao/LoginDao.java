package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.bank.Login;

public interface LoginDao {

	public List<Login> getAllLogins() throws SQLException;

	public void insertLogin(Login login) throws SQLException;

	@Deprecated
	public Login getLoginByUsername(String username) throws SQLException;
	
	public Login getLoginById(String usernameOrUserid) throws SQLException;
	
	public boolean deleteUser(Integer iUserId) throws SQLException;
	
	public void updateUser(Integer iUserId, String name) throws SQLException;
}
