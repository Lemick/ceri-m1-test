package fr.univavignon.pokedex.impl;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;

public class PokedexFactory implements IPokedexFactory {

	@Override
	public IPokedex createPokedex(IPokemonMetadataProvider pokeMetadataProvider, IPokemonFactory pokeFactory) {
		return new Pokedex(pokeMetadataProvider, pokeFactory);
	}
}
