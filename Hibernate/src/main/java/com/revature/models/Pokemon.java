package com.revature.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"trainer"})
public class Pokemon {
	private int id;
	private String name;
	private Type type;
	
	@JsonBackReference
	private Trainer trainer;
}
