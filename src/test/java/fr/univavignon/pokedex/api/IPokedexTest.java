package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


public class IPokedexTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private IPokedex pokedex;

    private int pSize =  0;
    
    private Pokemon pikachu = new Pokemon(0,"pikachu",80,90,70,60,50,40,43,26);
    private Pokemon ronflex = new Pokemon(0,"ronflex",78,56,45,34,23,12,2,23);
    

    @Before
    public void setUp() throws PokedexException {

        when(pokedex.size()).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
              return pSize;
            }
          });
        
        when(pokedex.addPokemon(any())).then(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
              return pSize++;
            }
          });
        
        when(pokedex.getPokemon(0)).thenReturn(pikachu);
        when(pokedex.getPokemon(1)).thenThrow(new PokedexException("Index Invalide"));

        List<Pokemon> pList = new ArrayList<>();
        List<Pokemon> pList2 = new ArrayList<>();
        pList.add(pikachu);
        pList.add(ronflex);

        when(pokedex.getPokemons()).thenReturn(Collections.unmodifiableList(pList));
        pList2.add(ronflex);
        pList2.add(pikachu);
        when(pokedex.getPokemons(any())).thenReturn(Collections.unmodifiableList(pList2)).thenReturn(Collections.unmodifiableList(pList));
  
    }
    
    
    @Test
    public void testAddPokemon() {
        assertEquals(0, pokedex.addPokemon(pikachu));
        assertEquals(1, pokedex.addPokemon(ronflex));
        assertEquals(2, pokedex.size());
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        pokedex.addPokemon(pikachu);

        assertEquals("pikachu", pokedex.getPokemon(0).getName());
    }


    @Test
    public void testGetPokemonsOrder() throws PokedexException {
        pokedex.addPokemon(pikachu);
        pokedex.addPokemon(ronflex);
        List<Pokemon> listName = pokedex.getPokemons(PokemonComparators.NAME);
        List<Pokemon> listIndex = pokedex.getPokemons(PokemonComparators.INDEX);
        List<Pokemon> listCP = pokedex.getPokemons(PokemonComparators.CP);
        assertEquals(0, listName.indexOf(ronflex));
        assertEquals(1, listIndex.indexOf(ronflex));
        assertEquals(1, listIndex.indexOf(ronflex));
    }

    @Test
    public void testGetPokemons() throws PokedexException {
        pokedex.addPokemon(pikachu);
        pokedex.addPokemon(ronflex);
        List<Pokemon> list = pokedex.getPokemons();

        assertEquals(pokedex.size(), list.size());
    }
    

}
