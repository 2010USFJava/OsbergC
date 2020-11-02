package com.revature.console;

import java.io.Serializable;

public class Animal implements Serializable {
	private static final long serialVersionUID = -3793849281670769995L;
	private String locomotion;
	private String diet;
	private String reproduction;
	private long age;
	
	public Animal() {
		super();
	}

	public Animal(String locomotion, String diet, String reproduction, long age) {
		super();
		this.locomotion = locomotion;
		this.diet = diet;
		this.reproduction = reproduction;
		this.age = age;
	}

	public String getLocomotion() {
		return locomotion;
	}

	public void setLocomotion(String locomotion) {
		this.locomotion = locomotion;
	}

	public String getDiet() {
		return diet;
	}

	public void setDiet(String diet) {
		this.diet = diet;
	}

	public String getReproduction() {
		return reproduction;
	}

	public void setReproduction(String reproduction) {
		this.reproduction = reproduction;
	}

	public long getAge() {
		return age;
	}

	public void setAge(long age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Animal [locomotion=" + locomotion + ", diet=" + diet + ", reproduction=" + reproduction + ", age=" + age
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (age ^ (age >>> 32));
		result = prime * result + ((diet == null) ? 0 : diet.hashCode());
		result = prime * result + ((locomotion == null) ? 0 : locomotion.hashCode());
		result = prime * result + ((reproduction == null) ? 0 : reproduction.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (age != other.age)
			return false;
		if (diet == null) {
			if (other.diet != null)
				return false;
		} else if (!diet.equals(other.diet))
			return false;
		if (locomotion == null) {
			if (other.locomotion != null)
				return false;
		} else if (!locomotion.equals(other.locomotion))
			return false;
		if (reproduction == null) {
			if (other.reproduction != null)
				return false;
		} else if (!reproduction.equals(other.reproduction))
			return false;
		return true;
	}
	
}
