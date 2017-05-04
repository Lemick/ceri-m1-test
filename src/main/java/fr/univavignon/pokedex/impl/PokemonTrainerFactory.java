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
		PokemonTrainer trainer = loadTrainer(name);
		if(trainer == null) { // On crée l'instance si pas de sauvegarde locale
			PokemonMetadataProvider metadataProvider = new PokemonMetadataProvider();
			PokemonFactory pokeFactory = new PokemonFactory();
			Pokedex pokedex = (Pokedex) pokedexFactory.createPokedex(metadataProvider, pokeFactory);
			trainer = new PokemonTrainerPersistable(name, team, pokedex);
			pokedex.addObserver((EditableObserver) trainer);
		} 
		return trainer;
	}
	
	public PokemonTrainerPersistable loadTrainer(String trainerName) {
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
			
		} catch (ClassNotFoundException e) {
			System.err.println("ClassNotFound loadTrainer");
		} catch (IOException e) {
			System.err.println("IOException loadTrainer");
		} finally {
			try {
				if(f != null)
					f.close();
				if(ois != null)
					ois.close();
			} catch (IOException e) {
				System.err.println("IOException loadTrainer");
			}	
		}
		return res;
	}

}
