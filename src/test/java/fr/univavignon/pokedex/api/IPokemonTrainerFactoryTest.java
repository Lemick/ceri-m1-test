package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public final class IPokemonTrainerFactoryTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private IPokemonTrainerFactory pokemonTrainerFactoryMock;

    @Mock
    private IPokedexFactory pokedexFactoryMock;

    @Mock
    private IPokedex pokedexMock;
    
    @Before
    public void setUp() throws PokedexException {
        when(pokemonTrainerFactoryMock.createTrainer("Pierre", Team.VALOR, pokedexFactoryMock))
        .thenReturn(new PokemonTrainer("Pierre", Team.VALOR, pokedexMock));
        
        when(pokedexMock.size()).thenReturn(1);
    }

    @Test
    public void testCreateTrainer() {
        PokemonTrainer sacha = pokemonTrainerFactoryMock.createTrainer("Pierre", Team.VALOR, pokedexFactoryMock);
        assertNotNull(sacha);
        assertNotNull(sacha.getPokedex());
        assertEquals(1, sacha.getPokedex().size());
        assertEquals("Pierre", sacha.getName());
        assertEquals(Team.VALOR, sacha.getTeam());
        
    }
}
