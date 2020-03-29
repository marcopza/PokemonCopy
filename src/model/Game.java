package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import Exception.PlayerAlreadyExistsException;
import Exception.PlayerNotFoundException;
import Exception.PokemonNotFoundException;

public class Game {

	//Atributes
	private ArrayList<Player> players;
	private Player playerSelected;
	private ArrayList<Pokemon> pokemons;
	private Pokemon pokemonSelected;
	
	public Game(Player playerSelected) {
		this.playerSelected = playerSelected;
		try {
			this.players = getPlayersList();
			this.pokemons = getPokemonList();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Player getPlayerSelected() {
		return playerSelected;
	}

	public void setPlayerByNickname(String nickname) {
		for(Player player : players) {
			if(player.getNickname().equals(nickname)) {
				setPlayerSelectedObject(player);
				break;
			}
		}
	}
	
	public void setPlayerSelectedObject(Player playerSelected) {
		this.playerSelected = playerSelected;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayerScoreCatch(long duration, long clickTime) {
		if((clickTime/duration) <= (0.25*duration)) {
			getPlayerSelected().setScore(4);
		}
		else if(((clickTime/duration) >= (0.25*duration)) && (clickTime/duration) <= (0.5*duration)) {
			getPlayerSelected().setScore(3);
		}
		else if(((clickTime/duration) >= (0.5*duration)) && (clickTime/duration) <= (0.75*duration)) {
			getPlayerSelected().setScore(2);
		}
		else {
			getPlayerSelected().setScore(1);
		}
		for(Player player : players) {
			if(player.getNickname().equals(playerSelected.getNickname())) {
				int index = players.indexOf(player);
				players.remove(index);
				players.add(playerSelected);
				break;
			}
		}
	}
	
	public void setPlayerScoreThrow(int duration) {
		if(duration == 1500) {
			getPlayerSelected().setScore(4);
		}
		else if((duration > 1500) && (duration <= 2750)) {
			getPlayerSelected().setScore(3);
		}
		else if((duration > 2750) && (duration <= 3375)) {
			getPlayerSelected().setScore(2);
		}
		else {
			getPlayerSelected().setScore(1);
		}
		for(Player current : players) {
			if(current.equals(getPlayerSelected())) {
				
			}
		}
		for(Player player : players) {
			if(player.getNickname().equals(playerSelected.getNickname())) {
				int index = players.indexOf(player);
				players.remove(index);
				players.add(playerSelected);
				break;
			}
		}
	}
	
	public String getPlayerByName(String nickname) throws PlayerNotFoundException {
		if(!checkIfPLayerExists(new Player(nickname,0))){
			throw new PlayerNotFoundException("The player you're searching for does not exist");
		}
		else {
			boolean encontre = false;
			int posicion = 0;
			int inicio = 0;
			ArrayList<Player> ordenado = organizePartialOrder();
			int fin = ordenado.size()-1;
			while(inicio <= fin && !encontre) {
				int medio = (inicio + fin) / 2;
				if(((Player)ordenado.get(medio)).getNickname().compareToIgnoreCase(nickname) == 0) {
					encontre = true; 
					posicion = medio;
				}
				else if(((Player)ordenado.get(medio)).getNickname().compareToIgnoreCase(nickname) > 0) {
					fin = medio - 1;
				}else {
					inicio = medio + 1;
				}
			}
			return ((Player) ordenado.get(posicion)).toString();
		}
	}
	
	public boolean checkIfPLayerExists(Player player1){
		ArrayList<Player> listOfPlayers = getPlayers();
		for(Player player : listOfPlayers) {
			if(player1.getNickname().equals(player.getNickname())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkIfPokemonExists(Pokemon pokemon) {
		ArrayList<Pokemon> listOfPokemon = getPokemonList();
		for(Pokemon pokemon1 : listOfPokemon) {
			if(pokemon1.getName().equals(pokemon.getName())) {
				return true;
			}
		}
		return false;
	}
	
	private ArrayList<Pokemon> getPokemonList() {
		if(pokemons == null) {
			ArrayList<Pokemon> list = new ArrayList<Pokemon>();
			list.add(new Pokemon("Charmeleon"));
			list.add(new Pokemon("Cyndaquil"));
			list.add(new Pokemon("Totodile"));
			list.add(new Pokemon("Lucario"));
			list.add(new Pokemon("Quilava"));
			list.add(new Pokemon("Vulpix"));
			return list;
		}else {
			return pokemons;
		}
		/*
		 * This part of the code was meant to be a serialization of the ArrayList, containing Pokemon objects, 
		 * said serialization could not be done since the Pokemon objects contain instances of javafx.Image
		 * objects, this means the class Pokemon is not serializable. Such serialization, could be done by using 
		 * technologies similar to io.Serializable but not the ones taught in class. 
		 * This ArrayList is always created as the same considering the pokemons displayed and eligible
		 * will always be the same. 
		 */
	}
	
	public void setPokemonSelected(String namePokemon){
		for(Pokemon pokemon : pokemons) {
			if(pokemon.getName().equals(namePokemon)) {
				this.pokemonSelected = pokemon;
				break;
			}
		}
	}
	
	public Pokemon getPokemonSelected() {
		return pokemonSelected;
	}

	public ArrayList<Pokemon> organizePokemons(){
		ArrayList<Pokemon> pokemonOrderArray = (ArrayList<Pokemon>) getPokemonList().clone();
		File partialOrderList = new File("OrganizedPokemons.txt");
		try {
			PrintWriter writer = new PrintWriter(partialOrderList);
			Pokemon temp;  
			Pokemon now;
	        for(int i=0; i < pokemonOrderArray.size(); i++){  
	                 for(int j=1; j < (pokemonOrderArray.size()-i); j++){  
	                	 now = (Pokemon)pokemonOrderArray.get(j-1);
	                          if(now.compareTo(pokemonOrderArray.get(j)) > 0){  
	                                 temp = now;
	                                 pokemonOrderArray.set(j-1, (Pokemon)pokemonOrderArray.get(j));
	                                 pokemonOrderArray.set(j, temp);
	                         }  
	                          
	                 }  
	        } 
			for(Pokemon pokemon : pokemonOrderArray) {
				writer.write(pokemon.toString()+"\n");
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return pokemonOrderArray;
	}
	
	public String getPokemonByName(String name) throws PokemonNotFoundException {
		if(!checkIfPokemonExists(new Pokemon(name))){
			throw new PokemonNotFoundException("The pokemon you're searching for isn't available :c");
		}
		else {
			boolean encontre = false;
			int posicion = 0;
			int inicio = 0;
			ArrayList<Pokemon> ordenado = organizePokemons();
			int fin = ordenado.size()-1;
			while(inicio <= fin && !encontre) {
				int medio = (inicio + fin) / 2;
				if(((Pokemon)ordenado.get(medio)).getName().compareToIgnoreCase(name) == 0) {
					encontre = true; 
					posicion = medio;
				}
				else if(((Pokemon)ordenado.get(medio)).getName().compareToIgnoreCase(name) > 0) {
					fin = medio - 1;
				}else {
					inicio = medio + 1;
				}
			}
			return ((Pokemon) ordenado.get(posicion)).toString();
		}
	}
	
	public ArrayList<Player> getPlayersList() {
		File playersList = new File("PlayersList.txt");
		ArrayList<Player> list = new ArrayList<Player>();
		if(playersList.exists()) {
			try {
				FileInputStream fis = new FileInputStream(playersList);
				ObjectInputStream ois = new ObjectInputStream(fis);
				while(fis.available() > 0) {
					list = (ArrayList<Player>) ois.readObject(); 
				}
				fis.close();
				ois.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		else {
			try {
				FileOutputStream fos = new FileOutputStream(playersList);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				list.add(new Player("Hedwig", 149));
				list.add(new Player("Robert", 108));
				list.add(new Player("Darrel", 146));
				list.add(new Player("Oren", 117));
				list.add(new Player("Deanna", 127));
				list.add(new Player("Nerea", 109));
				list.add(new Player("Emi", 67));
				list.add(new Player("Roanna", 144));
				list.add(new Player("Jakeem", 145));
				list.add(new Player("Claire", 74));
				list.add(new Player("Steven", 72));
				list.add(new Player("Emery", 87));
				list.add(new Player("Myles", 52));
				list.add(new Player("Azalia", 53));
				list.add(new Player("Yvonne", 60));
				oos.writeObject(list);
				oos.close();
				fos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public void createPlayer(Player player) throws PlayerAlreadyExistsException {
		ArrayList<Player> list = getPlayersList();
		File playersList = new File("PlayersList.txt");
		FileOutputStream fos;
		ObjectOutputStream ois;
		if(checkIfPLayerExists(player)) {
			throw new PlayerAlreadyExistsException("The player you're trying to create already exists");
		}
		else {
			try {
				fos = new FileOutputStream(playersList);
				ois = new ObjectOutputStream(fos);
				if(!list.contains(player)) {
						list.add(player);
				}
				ois.writeObject(list);
				fos.close();
				ois.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void savePlayersInfo(Player player){
		File playersList = new File("PlayersList.txt");
		FileOutputStream fos;
		ObjectOutputStream ois;
		for(Player now : players) {
			if(now.getNickname().equals(playerSelected.getNickname())) {
				int index = players.indexOf(now);
				players.remove(index);
				players.add(playerSelected);
				break;
			}
		}
		try {
			fos = new FileOutputStream(playersList);
			ois = new ObjectOutputStream(fos);
			ois.writeObject(players);
			fos.close();
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Player> organizeNaturalOrder() {
		ArrayList<Player> naturalOrderArray = (ArrayList<Player>) getPlayersList().clone();
		File naturalOrderList = new File("NaturalOrder.txt");
		try {
			PrintWriter writer = new PrintWriter(naturalOrderList);
			Player temp;  
			Player now;
	        for(int i=0; i < naturalOrderArray.size(); i++){  
	                 for(int j=1; j < (naturalOrderArray.size()-i); j++){  
	                	 now = (Player)naturalOrderArray.get(j-1);
	                          if(now.compare(now,naturalOrderArray.get(j)) < 0){  
	                                 temp = now;
	                                 naturalOrderArray.set(j-1, (Player)naturalOrderArray.get(j));
	                                 naturalOrderArray.set(j, temp);
	                         }  
	                          
	                 }  
	        } 
			for(Player player : naturalOrderArray) {
				writer.write(player.toString()+"\n");
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return naturalOrderArray;
	}
	
	public ArrayList<Player> organizePartialOrder() {
		ArrayList<Player> partialOrderArray = (ArrayList<Player>) getPlayersList().clone();
		File partialOrderList = new File("PartialOrder.txt");
		try {
			PrintWriter writer = new PrintWriter(partialOrderList);
			Player temp;  
			Player now;
	        for(int i=0; i < partialOrderArray.size(); i++){  
	                 for(int j=1; j < (partialOrderArray.size()-i); j++){  
	                	 now = (Player)partialOrderArray.get(j-1);
	                          if(now.compareTo(partialOrderArray.get(j)) > 0){  
	                                 temp = now;
	                                 partialOrderArray.set(j-1, (Player)partialOrderArray.get(j));
	                                 partialOrderArray.set(j, temp);
	                         }  
	                          
	                 }  
	        } 
			for(Player player : partialOrderArray) {
				writer.write(player.toString()+"\n");
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return partialOrderArray;
	}
}
