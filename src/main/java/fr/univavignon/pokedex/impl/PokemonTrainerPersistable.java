package fr.univavignon.pokedex.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;

public class PokemonTrainerPersistable extends PokemonTrainer implements Serializable, EditableObserver {

	private static final long serialVersionUID = 1L;
	public final String FILE_EXTENSION = ".ser";
	
	public PokemonTrainerPersistable(String name, Team team, IPokedex pokedex) {
		super(name, team, pokedex);
	}

	public void persist() {
		onChange();
	}
	
	
	@Override
	public void onChange() {
		FileOutputStream f = null;
		ObjectOutputStream o = null;
		try {
			f = new FileOutputStream(new File(getName() + FILE_EXTENSION));
			o = new ObjectOutputStream(f);
			o.writeObject(this);
		} catch (Exception e) {
			System.out.println("Error onChange " + e.getMessage());
		}  finally {
			try {
				f.close();
				o.close();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
	
	

}
