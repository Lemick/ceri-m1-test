package fr.univavignon.pokedex.api;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.File;


public class IPokemonTrainerFactoryTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    protected IPokemonTrainerFactory pokemonTrainerFactoryMock;

    @Mock
    protected IPokedexFactory pokedexFactoryMock;

    @Mock
    protected IPokedex pokedexMock;
    
    @Before
    public void setUp() throws PokedexException {
        when(pokemonTrainerFactoryMock.createTrainer("Pierre", Team.VALOR, pokedexFactoryMock))
        .thenReturn(new PokemonTrainer("Pierre", Team.VALOR, pokedexMock));
        
        when(pokedexMock.size()).thenReturn(1);
    }

    @Test
    public void testCreateTrainerNotExist() {
        PokemonTrainer sacha = pokemonTrainerFactoryMock.createTrainer("Pierre", Team.VALOR, pokedexFactoryMock);
        assertNotNull(sacha);
        assertNotNull(sacha.getPokedex());
        assertEquals("Pierre", sacha.getName());
        assertEquals(Team.VALOR, sacha.getTeam());  
    }
    
    
}
