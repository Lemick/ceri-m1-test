package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

public final class IPokemonMetadataProviderTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private static IPokemonMetadataProvider pokemonMetadataProviderMock;


    @Before
    public void setUp() throws PokedexException {
        when(pokemonMetadataProviderMock.getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0,"pika", 10,20,30));
    }

    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        PokemonMetadata pmetadata = pokemonMetadataProviderMock.getPokemonMetadata(0);
        
        assertNotNull(pmetadata);
        
        assertEquals("pika", pmetadata.getName());
        assertEquals(0, pmetadata.getIndex());
        assertEquals(10, pmetadata.getAttack());
        assertEquals(20, pmetadata.getDefense());
        assertEquals(30, pmetadata.getStamina());
        
    }
}
