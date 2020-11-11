package com.revature.bank;

import com.revature.banklogger.BankLogger;

/**
 * The QuitService class contains the functionality for quitting the program.
 * <p>
 * 
 * @author Christopher Osberg
 *
 */
public class QuitService extends Service {

	public QuitService() {
		super();
		serviceName = "Quit";
	}

	/**
	 * The performService method overrides the parent method in order to query and
	 * verify user input for further use.
	 * <p>
	 * 
	 * @param role The role parameter is the wrapper class identity for the user of
	 *             the program. It contains references to non-package classes.
	 * @return boolean Returns false, thereby ending the main menu loop and program.
	 */
	@Override
	public boolean performService(Role role) {
		BankLogger.logMessage("info", "The user quit the application.");
		return false;
	}

}
