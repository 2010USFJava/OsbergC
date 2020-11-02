package com.revature.driver;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.console.Animal;
import com.revature.console.AnimalLogger;
import com.revature.console.FileManager;

public class Driver {
	public static Scanner scanner = new Scanner(System.in);
	public static FileManager fileManager = new FileManager();

	public static void main(String[] args) {
		ArrayList<Animal> zoo;
		zoo = fileManager.readZooAnimals();
		while ((zoo = getChoice(zoo)) != null) {
		}
	}

	public static ArrayList<Animal> getChoice(ArrayList<Animal> zoo) {
		System.out.println("What would you like to do?");
		System.out.println("1. Create new Animal");
		System.out.println("2. Print Zoo Animals");
		System.out.println("3. Quit");
		int choice = 0;
		try {
			choice = Integer.parseInt(scanner.nextLine());
		} catch (Exception e) {
			System.out.println("Error: Please enter a valid number.");
			return zoo;
		}
		if (choice < 1 || choice > 3) {
			System.out.println("Error: Please enter a valid number.");
			return zoo;
		}
		switch (choice) {
		case 1:
			String locomotion;
			String diet;
			String reproduction;
			long age;
			System.out.println("Please enter the Animal's method of locomotion.");
			locomotion = scanner.nextLine();
			System.out.println("Please enter the Animal's diet.");
			diet = scanner.nextLine();
			System.out.println("Please enter the Animal's method of reproduction.");
			reproduction = scanner.nextLine();
			System.out.println("Please enter the Animal's age in seconds.");
			try {
				age = Long.parseLong(scanner.nextLine());
			} catch (Exception e) {
				System.out.println("Invalid age entered.");
				return zoo;
			}
			if (age < 0) {
				System.out.println("Invalid age entered.");
				return zoo;
			}
			zoo.add(new Animal(locomotion, diet, reproduction, age));
			AnimalLogger.logMessage("info", "Animal created with the fields:\nlocomotion: "+locomotion+
					"\ndiet: "+diet+"\nreproduction: "+reproduction+"\nage: "+age+"\n");
			fileManager.writeZooAnimals(zoo);
			break;
		case 2:
			System.out.println(zoo.toString());
			break;
		case 3:
			System.out.println("Thanks for visiting!");
			return null;
		default:
			System.out.println("Error: Please enter a valid number.");
			return zoo;
		}
		return zoo;
	}
}
