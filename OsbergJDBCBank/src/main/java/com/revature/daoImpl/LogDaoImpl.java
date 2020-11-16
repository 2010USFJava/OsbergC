package com.revature.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.dao.LogDao;
import com.revature.util.DatabaseConnectionFactory;

public class LogDaoImpl implements LogDao {
	
	public static DatabaseConnectionFactory dbConFac = DatabaseConnectionFactory.getInstance();

	@Override
	public void insertLog(String sMessage) throws SQLException {
		Connection connection = dbConFac.getConnection();
		String sqlQuery = "insert into logs (message) values (?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setString(1, sMessage);
		// Use executeUpdate to get number of rows changed
		preparedStatement.executeUpdate();
	}

}
