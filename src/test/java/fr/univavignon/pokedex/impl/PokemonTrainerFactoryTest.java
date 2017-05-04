package fr.univavignon.pokedex.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import fr.univavignon.pokedex.api.IPokemonTrainerFactoryTest;

public class PokemonTrainerFactoryTest extends IPokemonTrainerFactoryTest {

	PokemonTrainerPersistable trainer;
	
	@Override
	@Before
	public void setUp()  {
		Pokedex p = new Pokedex(null, null);
        when(pokedexFactoryMock.createPokedex(any(), any())).thenReturn(p);
		pokemonTrainerFactoryMock = new PokemonTrainerFactory();
	}

}
