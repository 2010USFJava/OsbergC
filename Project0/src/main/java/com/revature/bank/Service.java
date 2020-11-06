package com.revature.bank;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Scanner;

import com.revature.bank.RoleServices.roleName;

public abstract class Service {

	String serviceName;
	static InputStream inputStream;
	static OutputStream outputStream;
	Scanner scanner = new Scanner(System.in);

	public abstract boolean performService(Role role);

	public String getServiceName() {
		return serviceName;
	}

	Integer queryUserID(Role role) {
		Integer iUserID;
		if (role.getRoleName() == roleName.CUSTOMER) {
			iUserID = role.getUserID();
		} else {
			System.out.println("Enter the user's ID.");
			String sUserID = scanner.nextLine();
			try {
				iUserID = Integer.parseInt(sUserID);
			} catch (Exception e) {
				System.out.println("Error: Invalid input");
				return -1;
			}
		}
		return iUserID;
	}
	
	
}
