package com.revature.dao;

import java.sql.SQLException;

public interface LogDao {
	public void insertLog(String sMessage) throws SQLException;
}
