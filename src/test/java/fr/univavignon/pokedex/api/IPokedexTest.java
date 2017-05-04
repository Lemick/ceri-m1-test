package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


public class IPokedexTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    protected IPokedex pokedex;

    @Mock
    protected IPokedex pokedexAdd;
    
    protected int pSize =  0;
    
    protected Pokemon pikachu = new Pokemon(0,"pikachu",80,90,70,60,50,40,43,26);
    protected Pokemon ronflex = new Pokemon(0,"ronflex",78,56,45,70,23,12,2,23);
    

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
        pList.add(pikachu);
        pList.add(ronflex);

        when(pokedex.getPokemons()).thenReturn(Collections.unmodifiableList(pList));

        when(pokedex.getPokemons(PokemonComparators.NAME)).thenReturn(Collections.unmodifiableList(pList));
        when(pokedex.getPokemons(PokemonComparators.INDEX)).thenReturn(Collections.unmodifiableList(pList));
        when(pokedex.getPokemons(PokemonComparators.CP)).thenReturn(Collections.unmodifiableList(pList));
        
        when(pokedexAdd.addPokemon(pikachu)).thenReturn(0);
        when(pokedexAdd.addPokemon(ronflex)).thenReturn(1);
        when(pokedexAdd.size()).thenReturn(2);
       
    }
    
    
    @Test
    public void testAddPokemon() {
    	
        assertEquals(0, pokedexAdd.addPokemon(pikachu));
        assertEquals(1, pokedexAdd.addPokemon(ronflex));
        assertEquals(2, pokedexAdd.size());
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        assertEquals("pikachu", pokedex.getPokemon(0).getName());
    }


    @Test
    public void testGetPokemonsOrder() throws PokedexException {
        List<Pokemon> listName = pokedex.getPokemons(PokemonComparators.NAME);
        List<Pokemon> listIndex = pokedex.getPokemons(PokemonComparators.INDEX);
        List<Pokemon> listCP = pokedex.getPokemons(PokemonComparators.CP);
        assertEquals(1, listName.indexOf(ronflex));
        assertEquals(1, listIndex.indexOf(ronflex));
        assertEquals(1, listCP.indexOf(ronflex));
    }

    

}
