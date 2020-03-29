package test;

import java.util.ArrayList;

import junit.framework.TestCase;
import model.Game;
import model.Player;
import model.Pokemon;

public class GameTest extends TestCase{

	private Game game;
	private ArrayList<Player> result;
	private ArrayList<Pokemon> pokemonResult;
	
	public void setUpScenario1() {
		game = new Game(null);
		result = game.organizeNaturalOrder();
	}
	
	public void setUpScenario2() {
		game = new Game(null);
		result = game.organizePartialOrder();
	}
	
	public void setUpScenario3() {
		game = new Game(null);
		pokemonResult = game.organizePokemons();
	}
	
	public void testOrganizeNaturalOrder() {
		setUpScenario1();
		ArrayList<Player> expected = new ArrayList();
		expected.add(new Player("Hedwig", 149));
		expected.add(new Player("Darrel", 146));
		expected.add(new Player("Jakeem", 145));
		expected.add(new Player("Roanna", 144));
		expected.add(new Player("Deanna", 127));
		expected.add(new Player("Oren", 117));
		expected.add(new Player("Nerea", 109));
		expected.add(new Player("Robert", 108));
		expected.add(new Player("Emery", 87));
		expected.add(new Player("Claire", 74));
		expected.add(new Player("Steven", 72));
		expected.add(new Player("Emi", 67));
		expected.add(new Player("Yvonne", 60));
		expected.add(new Player("Azalia", 53));
		expected.add(new Player("Myles",52));
		for(int i = 0; i < result.size(); i++) {
			int scoreResulted = result.get(i).getScore();
			int scoreExpected = expected.get(i).getScore();
			assertEquals(scoreExpected, scoreResulted);
		}
	}
	
	public void testOrganizePartialOrder() {
		setUpScenario2();
		ArrayList<Player> expected = new ArrayList();
		expected.add(new Player("Azalia", 53));
		expected.add(new Player("Claire", 74));
		expected.add(new Player("Darrel", 146));
		expected.add(new Player("Deanna", 127));
		expected.add(new Player("Emery", 87));
		expected.add(new Player("Emi", 67));
		expected.add(new Player("Hedwig", 149));
		expected.add(new Player("Jakeem", 145));
		expected.add(new Player("Myles",52));
		expected.add(new Player("Nerea", 109));
		expected.add(new Player("Oren", 117));
		expected.add(new Player("Roanna", 144));
		expected.add(new Player("Robert", 108));
		expected.add(new Player("Steven", 72));
		expected.add(new Player("Yvonne", 60));
		for(int i = 0; i < result.size(); i++) {
			String nicknameResulted = result.get(i).getNickname();
			String nicknameExpected = expected.get(i).getNickname();
			assertEquals(nicknameExpected, nicknameResulted);
		}
	}
	
	public void testOrganizePokemons() {
		setUpScenario3();
		ArrayList<Pokemon> expected = new ArrayList();
		expected.add(new Pokemon("Charmeleon"));
		expected.add(new Pokemon("Cyndaquil"));
		expected.add(new Pokemon("Lucario"));
		expected.add(new Pokemon("Quilava"));
		expected.add(new Pokemon("Totodile"));
		expected.add(new Pokemon("Vulpix"));
		for(int i = 0; i < pokemonResult.size(); i++) {
			String nameResulted = pokemonResult.get(i).getName();
			String nameExpected = expected.get(i).getName();
			assertEquals(nameExpected, nameResulted);
		}
	}
	
}
