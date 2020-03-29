package model;

import java.io.Serializable;
import java.util.Comparator;

public class Player implements Serializable, Comparator<Player>, Comparable<Player>{

	//Atributes
	private String nickname; 
	private int score;
	
	public Player(String nickname, int score) {
		this.nickname = nickname;
		this.score = score;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int scoreToAdd) {
		score = score + scoreToAdd;
	}
	
	@Override
    public int compareTo(Player p) {
        return this.nickname.compareTo(p.nickname);
    }
	
	@Override
    public int compare(Player p1, Player p2) {
        return p1.getScore() - p2.getScore();
    }
	    
	@Override
	public String toString() {
		return nickname + "\t" + score;
	}
	
}
