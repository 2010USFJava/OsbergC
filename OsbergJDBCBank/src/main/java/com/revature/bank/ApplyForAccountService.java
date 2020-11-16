package com.revature.bank;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.banklogger.BankLogger;
import com.revature.exception.DuplicateUserException;
import com.revature.exception.InvalidInputException;
import com.revature.exception.UserDoesNotExistException;
import com.revature.util.InputVerifier;

/**
 * The ApplyForAccountService class contains the functionality for applying for
 * a bank account.
 * <p>
 * 
 * @author Christopher Osberg
 *
 */

public class ApplyForAccountService extends Service {

	public ApplyForAccountService() {
		super();
		serviceName = "Apply for an Account";
	}

	/**
	 * The performService method overrides the parent method in order to query and
	 * verify user input for further use.
	 * <p>
	 * 
	 * @param role The role parameter is the wrapper class identity for the user of
	 *             the program. It contains references to non-package classes.
	 * @return boolean The return type determines if the main menu loop with
	 *         continue functioning.
	 */
	@Override
	public boolean performService(Role role) {
		System.out.println("Please specify what kind of account you would like:");
		System.out.println("1. Checking Account");
		System.out.println("2. Savings Account");
		String sAccountType = scanner.nextLine();
		Integer iAccountType = null;
		try {
			iAccountType = InputVerifier.verifyIntegerInput(sAccountType, 0, 3);
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
			return true;
		}
		System.out.println("Is this a joint account?");
		System.out.println("1. Yes");
		System.out.println("2. No");
		String sIsJointAccount = scanner.nextLine();
		Integer iIsJointAccount = null;
		try {
			iIsJointAccount = InputVerifier.verifyIntegerInput(sIsJointAccount, 0, 3);
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
			return true;
		}
		ArrayList<Integer> accountUserIds = new ArrayList<>();
		accountUserIds.add(role.getUserId());
		switch (iIsJointAccount) {
		case 1:
			System.out.println("Please enter the user ID of the other user.");
			String sUserId = scanner.nextLine();
			Integer iUserId = null;
			try {
				iUserId = InputVerifier.verifyIntegerInput(sUserId, 0, Integer.MAX_VALUE);
				checkDuplicateUsers(role, iUserId);
				Login login = role.getLdi().getLoginById(sUserId);
				if (login != null) {
					accountUserIds.add(iUserId);
					createAccountApplication(role, iAccountType, accountUserIds);
				} else {
					throw new UserDoesNotExistException("Exception: User does not exist");
				}
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
				return true;
			} catch (DuplicateUserException e) {
				System.out.println(e.getMessage());
				return true;
			} catch (UserDoesNotExistException e) {
				System.out.println(e.getMessage());
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			createAccountApplication(role, iAccountType, accountUserIds);
			break;
		default:
			break;
		}
		return true;
	}

	private boolean checkDuplicateUsers(Role role, Integer iUserID) {
		boolean duplicateUsers = iUserID.intValue() == role.getUserId();
		if (duplicateUsers) {
			throw new DuplicateUserException("Exception: Duplicate user");
		}
		return duplicateUsers;
	}

	/**
	 * The createAccountApplication method obtains an account number, instantiates a
	 * new account, and writes all applications to a file.
	 * <p>
	 * 
	 * @param role The role parameter is the wrapper class identity for the user of
	 *             the program. It contains references to non-package classes.
	 * @return boolean The return type determines if the main menu loop with
	 *         continue functioning.
	 */
	private void createAccountApplication(Role role, Integer iAccountType, ArrayList<Integer> userIds) {
		try {
			switch (iAccountType) {
			case 1:
				role.getAdi().insertAccount(new Account(null, "checking", userIds, BigDecimal.ZERO));

				BankLogger.logMessage("info",
						"User number(s) " + userIds.toString() + " applied for a checking account.\n");
				break;
			case 2:
				role.getAdi().insertAccount(new Account(null, "savings ", userIds, BigDecimal.ZERO));
				BankLogger.logMessage("info",
						"User number(s) " + userIds.toString() + " applied for a savings account.\n");
				break;
			default:
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
