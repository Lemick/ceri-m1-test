package fr.univavignon.pokedex.impl;

import fr.univavignon.pokedex.api.IPokemonFactoryTest;
import fr.univavignon.pokedex.api.PokedexException;

public class PokemonFactoryTest extends IPokemonFactoryTest {

	@Override
	public void setUp() throws PokedexException {
		pokemonFactoryMock = new PokemonFactory();
	}
	
}
