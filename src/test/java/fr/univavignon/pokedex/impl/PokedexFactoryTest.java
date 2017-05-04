package fr.univavignon.pokedex.impl;

import fr.univavignon.pokedex.api.IPokedexFactoryTest;
import fr.univavignon.pokedex.api.Pokemon;

public class PokedexFactoryTest extends IPokedexFactoryTest {

	@Override
	public void setUp() {
		pokedexFactoryMock = new PokedexFactory();
	}
}
