package com.revature.banklogger;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daoImpl.LogDaoImpl;

/**
 * The BankLogger class contains the functionality for logging information to a
 * file.
 * <p>
 * 
 * @author Christopher Osberg
 *
 */
public class BankLogger {
	static Logger logger = LogManager.getLogger();
	static LogDaoImpl ldi = new LogDaoImpl();

	/**
	 * The logMessage method logs a given message to the file.
	 */
	public static void logMessage(String level, String message) {
		switch (level) {
		case "debug":
			logger.debug(message);
			break;
		case "warn":
			logger.warn(message);
			break;
		case "error":
			logger.error(message);
			break;
		case "fatal":
			logger.fatal(message);
			break;
		case "info":
			try {
				ldi.insertLog(message.substring(0, message.length() - 1));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			logger.info(message);
			break;
		case "trace":
			logger.trace(message);
			break;
		default:
			System.err.println("Level not recognized");
		}
	}

	/**
	 * The logReadItems method logs the item read from a file.
	 */
	@Deprecated
	public static <T> void logReadItems(T item) {
		logMessage("info", item.getClass().getSimpleName() + " read in:\n" + item + "\n");
	}

	/**
	 * The logWriteItems method logs the item written to a file.
	 */
	@Deprecated
	public static <T> void logWriteItems(T item) {
		logMessage("info", item.getClass().getSimpleName() + " written to file:\n" + item + "\n");
	}
}
