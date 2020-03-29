package model;

import javafx.scene.image.Image;

/**
*Pokemon class of the model.
*@author Marco Pérez.
*@version 16.09.2018
*/

public class Pokemon implements Comparable<Pokemon>{
	
	public static final String CHARMELEON = "file:images/Charmeleon.png";
	public static final String CYNDAQUIL = "file:images/Cyndaquil.png";
	public static final String TOTODILE = "file:images/Totodile.png";
	public static final String LUCARIO = "file:images/Lucario.png";
	public static final String QUILAVA = "file:images/Quilava.png";
	public static final String VULPIX = "file:images/Vulpix.png";
	
	private String name;
	private String pokemonSelected;
	private boolean catchable;
	
	public Pokemon(String namePokemonSelected) {
		name = namePokemonSelected;
		if(namePokemonSelected.equalsIgnoreCase("charmeleon")) {
			pokemonSelected = CHARMELEON;
		}else if(namePokemonSelected.equalsIgnoreCase("cyndaquil")) {
			pokemonSelected = CYNDAQUIL;
		}else if(namePokemonSelected.equalsIgnoreCase("totodile")) {
			pokemonSelected = TOTODILE;
		}else if(namePokemonSelected.equalsIgnoreCase("lucario")) {
			pokemonSelected = LUCARIO;
		}else if(namePokemonSelected.equalsIgnoreCase("quilava")) {
			pokemonSelected = QUILAVA;
		}else {
			pokemonSelected = VULPIX;
		}
		catchable = true;
	}
	
	public Image getImage() {
		return new Image(pokemonSelected);
	}
	
	public boolean isCatchable() {
		return catchable;
	}
	
	public void setCatchable(boolean catchable) {
		this.catchable = catchable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
    public int compareTo(Pokemon p) {
        return this.name.compareTo(p.name);
    }

	@Override
	public String toString() {
		return name;
	}
}
