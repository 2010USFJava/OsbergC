package com.revature.driver;

import com.revature.bean.Cat;
import com.revature.bean.Toy;

public class Driver {
	public static void main(String[] args) {
		Cat cat = new Cat("Tigger", "tabby", 1, 10f);
		Toy toy = new Toy("mouse", "blue");
		
		System.out.println(cat.toString());
		System.out.println(toy.toString());
		cat.play(toy);
		cat.setWeight(9.9f);
		System.out.println(cat.getName() + " now weighs " + cat.getWeight() + ".");
	}
}
