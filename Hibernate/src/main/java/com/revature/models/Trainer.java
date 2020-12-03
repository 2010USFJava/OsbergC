package com.revature.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trainers")
public class Trainer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column // not required, could be used to rename
	private String name;

	/*
	 * Hibernate Fetch Types:
	 * Eager -- Similar to the session inteface get method, will retrieve from the DB immediately
	 * Lazy -- Similar to the session interface load method, will not retrieve immediately, and will instead use a proxy
	 * 
	 * Hibernate Cascade Types:
	 * When Hibernate performs an operation on an entity, what should it do to any child entities?
	 * 
	 * ALL: Combination of all of the below types
	 * PERSIST: Operation to save the entity (If we save the trainer, do we also save the pokemon?)
	 * MERGE: Operation to update the entity (If we update the trainer, do we also update the pokemon?)
	 * REMOVE: Operation to delete the entity (If we delete the trainer, do we also delete the pokemon?)
	 * DETACH: Operation to remove the entity from the cache (If the trainer false out of the cache, should the pokemon also fall out?)
	 * REFRESH: Operation to reload the cache from the database (If we reload the trainer data from DB, do we also do so for the pokemon?)
	*/
	@JsonManagedReference
	@OneToMany(mappedBy = "trainer", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH })
	private List<Pokemon> pokemon;
}
