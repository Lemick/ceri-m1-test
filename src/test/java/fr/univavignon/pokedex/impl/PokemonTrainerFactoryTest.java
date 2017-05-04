package fr.univavignon.pokedex.impl;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univavignon.pokedex.api.IPokemonTrainerFactoryTest;
import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;
import fr.univavignon.pokedex.data.FileUtil;

public class PokemonTrainerFactoryTest extends IPokemonTrainerFactoryTest {

	PokemonTrainerPersistable trainer;
	
	@Override
	@Before
	public void setUp()  {
		Pokedex p = new Pokedex(null, null);
        when(pokedexFactoryMock.createPokedex(any(), any())).thenReturn(p);
		pokemonTrainerFactoryMock = new PokemonTrainerFactory();
		
		/** Copy sample data **/
		File src = FileUtil.getFile("Clay.ser");
		try {
			Files.copy(src.toPath(), new File("Clay.ser").toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    @Test
    public void testCreateTrainerExist() { // Test dans le cas ou un fichier trainer existe
        PokemonTrainer sacha = pokemonTrainerFactoryMock.createTrainer("Clay", Team.VALOR, pokedexFactoryMock);
        assertNotNull(sacha);
        assertNotNull(sacha.getPokedex());
        assertEquals("Clay", sacha.getName());
        assertEquals(Team.MYSTIC, sacha.getTeam()); 
    }

    @After
    public void end() {
    	File file = new File("Clay.ser");
    	if(file.exists())
    		file.delete();
    	file = new File("Pierre.ser");
    	if(file.exists())
    		file.delete();
    }
}
