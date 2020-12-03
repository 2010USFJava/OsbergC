package com.revature;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Pokemon;
import com.revature.models.Trainer;
import com.revature.models.Type;
import com.revature.repositories.PokemonDao;
import com.revature.repositories.PokemonDaoImpl;
import com.revature.util.HibernateUtil;

public class Driver {

	public static void main(String[] args) {
//		// Instantiated in transient state
//		Pokemon p = new Pokemon(0, "Bulbasaur", Type.Grass, null);
//		System.out.println(p.getName());
//
//		Session s = HibernateUtil.getSession();
//		
//
//		Transaction tx = s.beginTransaction();
//
//		// Will perform either insert or update
//		// not applied until transaction is committed
//		s.save(p);
//		// p is not persistent
//		
//		Pokemon p2 = new Pokemon(1, "Charmander", Type.Fire, null);
//		s.save(p2);
//
//		tx.commit();
//
//		Pokemon fromDB = s.get(Pokemon.class, 1);
//		System.out.println("Pokemon from java = " + p + "\n\rPokemon from DB = " + fromDB + "\n\rThe pokemon are equal. " + p.equals(fromDB));
//
//		Pokemon fromDB2 = s.get(Pokemon.class, 2);
//		System.out.println("Pokemon from java = " + p2 + "\n\rPokemon from DB = " + fromDB2 + "\n\rThe pokemon are equal. " + p2.equals(fromDB2));
//		
//		PokemonDaoImpl pdi = new PokemonDaoImpl();
//		
//		List<Pokemon> all = pdi.findAll();
//		
////		Set<Pokemon> set = pdi.findAllAsSet();
//		
////		pdi.delete(all.get(1));
//		
//		Trainer ash = new Trainer(0, "Ash Ketchum", new ArrayList<>());
//		
//		for(Pokemon poke : all) {
//			poke.setTrainer(ash);
//			ash.getPokemon().add(poke);
//		}
//		
//		List<Pokemon> ashsPokemon = pdi.findByTrainer(ash);
//		System.out.println(ashsPokemon.equals(all));
//		
//		ObjectMapper om = new ObjectMapper();
//		
//		try {
//			System.out.println(om.writeValueAsString(ash));
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
		Pokemon p = new Pokemon(0, "Bulbasaur", Type.Grass, null);

		System.out.println(p.getName());

		Session s = HibernateUtil.getSession();

		Transaction tx = s.beginTransaction();

		// Will perform either an INSERT or an UPDATE
		s.save(p);

		// This Pokemon object is now PERSISTENT

		tx.commit();

		tx = s.beginTransaction();
//      Pokemon fromDB = s.get(Pokemon.class, 1);

		System.out.println(p);

//      System.out.println(fromDB);
//      
//      System.out.println(p.equals(fromDB));
//      System.out.println(p == fromDB);

		Pokemon p2 = new Pokemon(0, "Charmander", Type.Fire, null);

		s.save(p2);

//      Pokemon fromDB2 = s.get(Pokemon.class, 2);

//      System.out.println(fromDB2);

//      tx.rollback();

//      fromDB = s.get(Pokemon.class, 1);
//      fromDB2 = s.get(Pokemon.class, 2);

//      System.out.println(fromDB);
//      System.out.println(fromDB2);
		// Potential code to use for managing transactions with an application
//    Session s2 = HibernateUtil.getSession();
//    
//    Transaction tx2 = s2.beginTransaction();
//    
//    try {
//        
//        // Could throw the exception
//        
//        tx2.commit();
//    } catch (SQLException e) {
//        tx2.rollback();
//    }

		PokemonDao pokemonDAO = new PokemonDaoImpl();

		List<Pokemon> all = pokemonDAO.findAll();

		System.out.println(all);

		Set<Pokemon> set = pokemonDAO.findAllAsSet();

		System.out.println(set);

//    pokemonDAO.delete(all.get(1));
//    
//    set = pokemonDAO.findAllAsSet();
//    
//    System.out.println(set);
		System.out.println("----------------------------------------------");

		Trainer ash = new Trainer(0, "Ash Ketchum", new ArrayList<>());

		s.save(ash);

		for (Pokemon poke : all) {
			poke.setTrainer(ash);
			ash.getPokemon().add(poke);
		}

		List<Pokemon> ashsPokes = pokemonDAO.findByTrainer(ash);

		System.out.println(ashsPokes.equals(all));

		System.out.println(ashsPokes == all);

		System.out.println(ashsPokes);

		System.out.println(ash);

		ObjectMapper om = new ObjectMapper();

		try {
			System.out.println(om.writeValueAsString(ash));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
