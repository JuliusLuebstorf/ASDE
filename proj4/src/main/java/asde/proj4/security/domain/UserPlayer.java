package asde.proj4.security.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERPLAYER")
public class UserPlayer{

	@Id
	private String username;
	private String pass;
	private String email;
	
	@Column(name = "TICTACTOESCORE")
	private Integer ticTacToeScore;
	private long lastLoginTime;
	public UserPlayer(String username, String pass, String email) {
		super();
		this.username = username;
		this.pass = pass;
		this.email = email;
		this.ticTacToeScore = 0;
	}
	public UserPlayer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getTicTacToeScore() {
		return this.ticTacToeScore;
	}
	public void setTicTacToeScore(Integer score) {
		this.ticTacToeScore += score;
	}
	public long getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	

}
