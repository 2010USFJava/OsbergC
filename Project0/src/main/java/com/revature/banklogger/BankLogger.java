package com.revature.banklogger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BankLogger {
	static Logger logger = LogManager.getLogger();
	
	public static void logMessage(String level, String message) {
		switch(level) {
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
			logger.info(message);
			break;
		case "trace":
			logger.trace(message);
			break;
			default:
				System.err.println("Level not recognized");
		}
	}
	
	public static <T> void logReadItems(T item) {
		logMessage("info", item.getClass().getSimpleName() + "s read in:\n" + item + "\n");
	}
	
	public static <T> void logWriteItems(T item) {
		logMessage("info", item.getClass().getSimpleName() + "s written to file:\n" + item + "\n");
	}
}
