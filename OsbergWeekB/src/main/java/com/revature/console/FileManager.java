package com.revature.console;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileManager {
	ObjectInputStream objectInputStream;
	ObjectOutputStream objectOutputStream;
	File file = new File("zoo.txt");

	public void writeZooAnimals(ArrayList<Animal> zoo) {
		try {
			objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
			objectOutputStream.writeObject(zoo);
			objectOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Animal> readZooAnimals() {
		ArrayList<Animal> zoo = new ArrayList<Animal>();
		if (file.exists()) {
			try {
				// System.out.println(new File(".").getAbsolutePath());
				objectInputStream = new ObjectInputStream(new FileInputStream(file));
				zoo = (ArrayList<Animal>) objectInputStream.readObject();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return zoo;
	}
}
