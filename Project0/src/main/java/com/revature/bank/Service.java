package com.revature.bank;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public abstract class Service {

	String serviceName;
	static InputStream inputStream;
	static OutputStream outputStream;
	Scanner scanner = new Scanner(System.in);
	
	public boolean performService(Role role) {
		return true;
	}
	
	public String getServiceName() {
		return serviceName;
	}
}
