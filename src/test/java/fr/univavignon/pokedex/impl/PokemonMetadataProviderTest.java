package fr.univavignon.pokedex.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.univavignon.pokedex.api.IPokemonMetadataProviderTest;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.PokemonMetadata;
import fr.univavignon.pokedex.data.FileUtil;

public class PokemonMetadataProviderTest extends IPokemonMetadataProviderTest {
	
	private PokemonMetadataProvider pokemonMetadataProvider; // For testing uninterfaced methods
	private String JSONContentSample; 
	private ArrayList<Object> pMetadataCollec;
	
	@Override
	public void setUp() throws PokedexException {
		pokemonMetadataProviderMock = new PokemonMetadataProvider();
		pokemonMetadataProvider = new PokemonMetadataProvider();
		pMetadataCollec = new ArrayList<Object>() {{
		    add("pikachu");
		    add(55);
		    add(40);
		    add(90);
		}};
		JSONContentSample = FileUtil.getFileContent("PokemonMetadataProviderTestSample");
	}

	@Test
	public void testGetHTTPResponse() throws IOException {
		HttpURLConnection request = pokemonMetadataProvider.getHTTPResponse(25);
		
		assertNotNull(request);
		assertEquals(HttpURLConnection.HTTP_OK, request.getResponseCode());
	}
	
	@Test
	public void testGetMetadatasCollection()  {
		List<Object> result = pokemonMetadataProvider.getMetadatasCollection(JSONContentSample);
		
		assertNotNull(result);
		assertEquals(4, result.size());
		assertEquals("pikachu", result.get(0));
		assertEquals(55, result.get(1));
		assertEquals(40, result.get(2));
		assertEquals(90, result.get(3));	
	}
	
	@Test
	public void testConvertPokemonMetadatas()  {
		PokemonMetadata p = pokemonMetadataProvider.convertPokemonMetadatas(25, pMetadataCollec);
	
		assertNotNull(p);
		assertEquals(p.getName(), "pikachu");
		assertEquals(p.getAttack(), 55);
		assertEquals(p.getDefense(), 40);
		assertEquals(p.getStamina(), 90);
	}
	
	
}
