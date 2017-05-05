package fr.univavignon.pokedex.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokemonTrainerFactory;
import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;

public class PokemonTrainerFactory implements IPokemonTrainerFactory {

	@Override
	public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
		PokemonTrainerPersistable trainer = null;
		try {
			trainer = loadTrainer(name);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		if(trainer == null) { // On crée l'instance si pas de sauvegarde locale
			PokemonMetadataProvider metadataProvider = new PokemonMetadataProvider();
			PokemonFactory pokeFactory = new PokemonFactory();
			Pokedex pokedex = (Pokedex) pokedexFactory.createPokedex(metadataProvider, pokeFactory);
			trainer = new PokemonTrainerPersistable(name, team, pokedex);
			pokedex.addObserver(trainer);
			trainer.persist();
		} 
		return trainer;
	}
	
	public PokemonTrainerPersistable loadTrainer(String trainerName) throws IOException, ClassNotFoundException {
		FileInputStream  f = null;
		ObjectInputStream ois = null;
		PokemonTrainerPersistable res = null;
		
		try {
			File file = new File(trainerName  +".ser");
			if(file.exists() && !file.isDirectory()) { 
				f = new FileInputStream (file);
				ois = new ObjectInputStream(f);
				res = (PokemonTrainerPersistable) ois.readObject();
			} else {
				return null;
			}
		} finally {
				if(f != null)
					f.close();
				if(ois != null)
					ois.close();	
		}
		return res;
	}

}
