package sk.tsystems.gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Score {
	@Id
	@GeneratedValue
	private int ident;
	
	private String username;
	
	private String game;
	
	private int score;
	
	public Score() {
	}
	
	public Score(String username, String game, int score) {
		this.username = username;
		this.game = game;
		this.score = score;
	}
	
	public int getIdent() {
		return ident;
	}
	
	public void setIdent(int ident) {
		this.ident = ident;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGame() {
		return game;
	}
	public void setGame(String game) {
		this.game = game;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Score [ident=" + ident + ", username=" + username + ", game=" + game + ", score=" + score + "]";
	}
	
}
