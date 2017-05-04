package fr.univavignon.pokedex.impl;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokemonTrainerFactoryTest;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;

public class PokemonTrainerFactoryTest extends IPokemonTrainerFactoryTest {

	PokemonTrainerPersistable trainer;
	
	@Before
	public void setUp()  {
		Pokedex p = new Pokedex(null, null);
        when(pokedexFactoryMock.createPokedex(any(), any())).thenReturn(p);
		pokemonTrainerFactoryMock = new PokemonTrainerFactory();
	}

}
