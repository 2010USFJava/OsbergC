package com.revature.bank;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public abstract class Service {

	String serviceName;
	static InputStream inputStream;
	static OutputStream outputStream;
	Scanner scanner = new Scanner(System.in);

	public abstract boolean performService(Role role);

	public String getServiceName() {
		return serviceName;
	}
}
