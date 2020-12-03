package com.revature.repositories;

import java.util.List;
import java.util.Set;

import com.revature.models.Pokemon;
import com.revature.models.Trainer;

public interface PokemonDao {

	public Pokemon findById(int id);

	public Pokemon findByName(String name);

	public List<Pokemon> findAll();

	public Set<Pokemon> findAllAsSet();

	public List<Pokemon> findByTrainer(Trainer t);

	public void insert(Pokemon p);

	public void update(Pokemon p);

	public void delete(Pokemon p);
}
