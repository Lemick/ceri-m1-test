package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class IPokedexFactoryTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    protected IPokedexFactory pokedexFactoryMock;

    @Mock
    protected IPokemonMetadataProvider pokemonMetadataProviderMock;

    @Mock
    protected IPokemonFactory pokemonFactoryMock;
    
    @Mock
    protected IPokedex pokedexMock;

    @Before
    public void setUp() {
        when(pokedexFactoryMock.createPokedex(pokemonMetadataProviderMock, pokemonFactoryMock))
                .thenReturn(pokedexMock);
    }

    @Test
    public void testCreatePokedex() {
        IPokedex pokedex = pokedexFactoryMock.createPokedex(pokemonMetadataProviderMock, pokemonFactoryMock);
        assertNotNull(pokedex);

    }
}
