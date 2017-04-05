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
import static org.mockito.Mockito.when;


public class IPokemonFactoryTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    protected static IPokemonFactory pokemonFactoryMock;

    @Before
    public void setUp() throws PokedexException {
        when(pokemonFactoryMock.createPokemon(0, 0, 0, 0, 0))
        .thenReturn(new Pokemon(1, "Pika", 10, 20, 30, 40, 50, 60, 70, 80.0));
    }

    @Test
    public void testCreatePokemon() {
        Pokemon pokemon = pokemonFactoryMock.createPokemon(0, 0, 0, 0, 0);
        assertNotNull(pokemon);
        assertEquals("Pika", pokemon.getName());
        assertEquals(10, pokemon.getAttack());
        assertEquals(20, pokemon.getDefense());
        assertEquals(30, pokemon.getStamina());
        assertEquals(40, pokemon.getCp());
        assertEquals(50, pokemon.getHp());
        assertEquals(60, pokemon.getDust());
        assertEquals(70, pokemon.getCandy());
        assertEquals(80.0, pokemon.getIv());
    }
}
