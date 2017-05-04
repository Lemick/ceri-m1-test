package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;


public class IPokemonFactoryTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    protected IPokemonFactory pokemonFactoryMock;

    @Before
    public void setUp() throws PokedexException {
        when(pokemonFactoryMock.createPokemon(25, 20, 100, 20, 50))
        .thenReturn(new Pokemon(25, "pikachu", 55, 40, 90, 20, 100, 20, 50, 50.0));
    }

    @Test
    public void testCreatePokemon() {
        Pokemon pokemon = pokemonFactoryMock.createPokemon(25, 20, 100, 20, 50);
        assertNotNull(pokemon);
        assertEquals("pikachu", pokemon.getName());
        assertEquals(55, pokemon.getAttack());
        assertEquals(40, pokemon.getDefense());
        assertEquals(90, pokemon.getStamina());
        assertEquals(20, pokemon.getCp());
        assertEquals(100, pokemon.getHp());
        assertEquals(20, pokemon.getDust());
        assertEquals(50, pokemon.getCandy());
        assertEquals(50.0, pokemon.getIv());
    }
}
