package com.revature.bank;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.banklogger.BankLogger;

public class ViewTransactionsService extends Service {
	
	public ViewTransactionsService() {
		super();
		serviceName = "View Transactions";
	}

	@Override
	public boolean performService(Role role) {
		ArrayList<Transaction> transactions;
		BankLogger.logMessage("info", "Viewed transactions for user number " + role.getUserId() + ".\n");
		try {
			transactions = (ArrayList<Transaction>) role.getTdi().getUserTransactions(role.getUserId());
			for (Transaction transaction : transactions) {
				System.out.println("Date: " + transaction.getTimestamp() + "\tMessage: " + transaction.getsMessage() + "\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

}
