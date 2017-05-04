package fr.univavignon.pokedex.impl;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.Team;

public class PokemonTrainerPersistableTest {
	
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    protected IPokedex pokedexMock;
    
    private PokemonTrainerPersistable trainer;
    private String name;
    
    @Before 
    public void setUp() {
    	name = "ondine";
    	trainer = new PokemonTrainerPersistable(name, Team.INSTINCT, pokedexMock);	
	}
    
    @Test
    public void testPersist() {
    	
    	trainer.persist();
    	File file = new File(name + trainer.FILE_EXTENSION);
    	assertTrue(file.exists());
    }
    
    @After
    public void end() {
    	File file = new File(name + trainer.FILE_EXTENSION);
    	if(file.exists())
    		file.delete();
    }
}
