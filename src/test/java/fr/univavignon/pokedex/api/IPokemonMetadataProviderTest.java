package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class IPokemonMetadataProviderTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    protected IPokemonMetadataProvider pokemonMetadataProviderMock;


    @Before
    public void setUp() throws PokedexException {
        when(pokemonMetadataProviderMock.getPokemonMetadata(25)).thenReturn(new PokemonMetadata(0,"pikachu", 55,40,90));
    }

    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        PokemonMetadata p = pokemonMetadataProviderMock.getPokemonMetadata(25);
        
        assertTrue(p != null);
		assertEquals(p.getName(), "pikachu");
		assertEquals(p.getAttack(), 55);
		assertEquals(p.getDefense(), 40);
		assertEquals(p.getStamina(), 90);
        
    }
    
}
