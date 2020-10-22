package com.revature.chaining;

public class Cat {
	
	public int age;
	public float weight;
	public float height;
	public String color;
	public String name;
	
	enum Sex { male, female }
	
	public Sex sex;
	
	Cat() {
		this(5);
	}
	
	Cat(int age) {
		this(age, 10);
	}
	
	Cat(int age, float weight) {
		this(age, weight, 12);
	}
	
	Cat(int age, float weight, float height) {
		this(age, weight, height, "black");
	}
	
	Cat (int age, float weight, float height, String color) {
		this(age, weight, height, color, "Midnight");
	}
	
	Cat(int age, float weight, float height, String color, String name) {
		this(age, weight, height, color, name, Sex.male);
	}
	
	Cat (int age, float weight, float height, String color, String name, Sex sex) {
		this.age = age;
		this.weight = weight;
		this.height = height;
		this.color = color;
		this.name = name;
		this.sex = sex;
	}
	
	@Override
	public String toString() {
		return "Cat [age=" + age + ", weight=" + weight + ", height=" + height + ", color=" + color + ", name=" + name
				+ ", sex=" + sex + "]";
	}

	public static void main(String[] args) {
		Cat a = new Cat();
		System.out.println(a);
		Cat b = new Cat(2, 11, 14, "ginger");
		System.out.println(b);
		Cat c = new Cat(19, 9, 10, "calico", "Sunny", Sex.female);
		System.out.println(c);
	}

}
