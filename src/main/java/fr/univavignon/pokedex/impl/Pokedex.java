package fr.univavignon.pokedex.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;

public class Pokedex implements IPokedex, Serializable  {

	private static final long serialVersionUID = 1L;
	
	private List<Pokemon> lPokemon;
	private IPokemonMetadataProvider pokeMetadataProvider;
	private List<EditableObserver> observers;
	protected IPokemonFactory pokeFactory;

	
	public Pokedex(IPokemonMetadataProvider pokeMetadataProvider, IPokemonFactory pokeFactory) {
		this.pokeMetadataProvider = pokeMetadataProvider;
		this.pokeFactory = pokeFactory;
		lPokemon = new ArrayList<Pokemon>();
		observers = new ArrayList<EditableObserver>();
	}
	
	@Override
	public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
		if(index >= lPokemon.size() || index < 0) {
			throw new PokedexException("Index out of bounds");
		}
		return lPokemon.get(index);
	}

	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		return pokeFactory.createPokemon(index, cp, hp, dust, candy);
	}

	@Override
	public int size() {
		return lPokemon.size();
	}

	@Override
	public int addPokemon(Pokemon pokemon) {
		lPokemon.add(pokemon);
		return lPokemon.size()-1;
	}

	@Override
	public Pokemon getPokemon(int id) throws PokedexException {
		if(id >= lPokemon.size() || id < 0) {
			throw new PokedexException("Index out of bounds");
		}
		return lPokemon.get(id);
	}

	@Override
	public List<Pokemon> getPokemons() {
		return Collections.unmodifiableList(lPokemon);
	}

	@Override
	public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
		List<Pokemon> pokeList = new ArrayList<Pokemon>(lPokemon);
		Collections.sort(pokeList, order);
		return Collections.unmodifiableList(pokeList);
	}
	
	public void addObserver(EditableObserver e) {
		observers.add(e);
	}
	
	public void notifyObservers() {
		for(EditableObserver e : observers) {
			if(e != null) {
				e.onChange();
			}
		}
	}

	
}
