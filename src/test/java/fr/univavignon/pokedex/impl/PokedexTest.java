package fr.univavignon.pokedex.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mock;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokedexTest;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;

public class PokedexTest extends IPokedexTest{
	
	@Mock
	IPokemonMetadataProvider metaProviderMock;
	
	@Mock
	IPokemonFactory pokeFactoryMock;
	
	@Override
	public void setUp() throws PokedexException {
		pokedex = new Pokedex(metaProviderMock, pokeFactoryMock);
		when(pokeFactoryMock.createPokemon(25, 10, 100, 42, 0)).thenReturn(pikachu);
		pokedex.addPokemon(pikachu);
		pokedex.addPokemon(ronflex);
		
		/** Celui-ci doit être vide **/
		pokedexAdd = new Pokedex(metaProviderMock, pokeFactoryMock);
	}
	
	@Test (expected = PokedexException.class)
	public void testPokedexException() throws PokedexException {
		
		PokemonMetadataProvider pmp = new PokemonMetadataProvider();
		PokemonFactory pf = new PokemonFactory();
		Pokemon po = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 5);
		IPokedex ipo = new Pokedex(pmp, pf);
		ipo.addPokemon(po); 
		assertEquals(po.getAttack(),ipo.getPokemon(1).getAttack());
	}



	@Test
	public void testGetPokemonMetadata() {
		
		PokemonMetadataProvider pmp = new PokemonMetadataProvider();
		PokemonFactory pf = new PokemonFactory();
		Pokedex po = new Pokedex(pmp, pf);
		po.addPokemon(new Pokemon(0, "pikachu", 50, 60, 80, 10, 100, 20, 20, 0));
		
		try {
			po.getPokemonMetadata(-1);
		} 
		catch (Exception e) {
			assertEquals(e.getClass(), PokedexException.class);
			assertEquals("Index out of bounds", e.getMessage());
		}
		
		try {
			po.getPokemonMetadata(0);
		} 
		catch (Exception e) {
			assertEquals(e.getClass(), PokedexException.class);
			assertEquals("Index out of bounds", e.getMessage());
		}
		
		try {
			po.getPokemonMetadata(2);
		} 
		catch (Exception e) {
			assertEquals(e.getClass(), PokedexException.class);
			assertEquals("Index out of bounds", e.getMessage());
		}
		
		try {
			assertNotNull(po.getPokemonMetadata(0));
		} catch (PokedexException e) {
			fail();
		}
	}

	
}
