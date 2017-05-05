package fr.univavignon.pokedex.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jayway.restassured.path.json.JsonPath;

import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.PokemonMetadata;

public class PokemonMetadataProvider implements IPokemonMetadataProvider, Serializable {

	public final String[] stats = {
			  "attack", 
			  "defense", 
			  "speed"
			};

	private HashMap<Integer, PokemonMetadata> pokemonMetadatas;

	public PokemonMetadataProvider() {
		pokemonMetadatas = new HashMap<>();
	}
	
	@Override
	public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
		PokemonMetadata res = pokemonMetadatas.get(index);
		if (res != null)
			return res;
			
		String result;
		try {
			HttpURLConnection response = getHTTPResponse(index);
			result = getJSONContent(response);
			List<Object> metadatas = getMetadatasCollection(result);
			return convertPokemonMetadatas(index, metadatas);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;	
	}

	/**
	 * 
	 * @param index Index du pokémon Ã  intégrer Ã  la requÃªte
	 * @return Un objet représentant la réponse HTTP
	 * @throws IOException 
	 */
	public HttpURLConnection getHTTPResponse(int index) throws IOException {
		URL url = null;
		HttpURLConnection request = null;
		System.setProperty("http.agent", "Gonna catch them all"); // Set user-sagent
		url = new URL("http://pokeapi.co/api/v2/pokemon/" + index);
		request = (HttpURLConnection) url.openConnection();
		request.connect();
	
		return request;
	}
	/**
	 * Retourne le texte JSON lié à la requête
	 * @param request Requête http à lire
	 * @return le texte JSON
	 * @throws IOException 
	 */
	public String getJSONContent(HttpURLConnection request) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedInputStream is = null;
		BufferedReader br;

		try {
			is = new BufferedInputStream(request.getInputStream());
			br = new BufferedReader(new InputStreamReader(is));
			String inputLine = "";
			while ((inputLine = br.readLine()) != null) {
				sb.append(inputLine);
			}
		} finally {
			if (is != null) {
				is.close(); 
			}   
		}
		return sb.toString();
	}
	
	/**
	 * Retourne une liste d'objet représentant les métadonnées
	 * extraite du texte JSON
	 * @param jsonContent Le texte JSON
	 * @return La liste des métadonnées non converties
	 */
	public List<Object> getMetadatasCollection(String jsonContent) {
		List<Object> metadatas = new ArrayList<Object>(); 
		metadatas.add(JsonPath.from(jsonContent).get("name"));
		for(String stat : stats) {
			metadatas.addAll(JsonPath.from(jsonContent).get("stats.findAll { stats -> stats.stat.name == '" + stat + "' }.base_stat "));
		}
		return metadatas;
	}
	
	/**
	 * Convertit les données dans le bon format
	 * @param index Index du pokémon
	 * @param metadatas Les métadonnées à convertir
	 * @return Les métadonnées converties
	 */
	public PokemonMetadata convertPokemonMetadatas(int index, List<Object> metadatas) {
		String name = null;
		int attack = 0, defense = 0, stamina = 0;
		if(metadatas.size() == stats.length + 1) { // Having datas we need
			name = (String) metadatas.get(0);
			attack = (int)metadatas.get(1);
			defense = (int)metadatas.get(2);
			stamina = (int)metadatas.get(3);
		}
		return new PokemonMetadata(index, name, attack, defense, stamina);
	}
	

}
