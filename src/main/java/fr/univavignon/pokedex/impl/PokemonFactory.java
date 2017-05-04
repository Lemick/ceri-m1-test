package fr.univavignon.pokedex.impl;

import java.io.Serializable;

import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;

public class PokemonFactory implements IPokemonFactory, Serializable {

	private PokemonMetadataProvider pokemonMetadataProvider;
	
	
	public PokemonFactory() {
		pokemonMetadataProvider = new PokemonMetadataProvider();
	}
	
	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		Pokemon pokemon;
		PokemonMetadata pokemonMetadata;
		try {
			pokemonMetadata = pokemonMetadataProvider.getPokemonMetadata(index);
			if(pokemonMetadata == null) 
				return null;
		} catch (PokedexException e) {
			return null;
		}
		return new Pokemon(index, pokemonMetadata.getName(), pokemonMetadata.getAttack(), pokemonMetadata.getDefense(),
						pokemonMetadata.getStamina(), cp, hp, dust, candy, 50);
		
	}


}
