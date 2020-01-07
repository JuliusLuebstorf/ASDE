package asde.proj4.security.config.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Player {

	@Id
	private String name;
	
	@Column(nullable = false)
	private String pass;
	
	//@Transient
	//@JsonInclude
	@Column(nullable = false)
	private int coin;
	
	@Column(nullable = false)
	private Calendar dateOfJoin;
	
	
	public Player() {
		super();
	}
	
	
	public Player(String name, String pass, int coin) {
		super();
		this.name = name;
		this.pass = pass;
		this.coin = coin;
		this.dateOfJoin = Calendar.getInstance();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public int getCoin() {
		return coin;
	}
	public void setCoin(int coin) {
		this.coin = coin;
	}
	
	public void addCoin(int coin) {
		this.coin += coin;
	}
	
	public String getDateOfJoin() {
		
		
		
		try {
		SimpleDateFormat s = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.ENGLISH);
		return s.format(dateOfJoin.getTime());
		}
		catch(Exception e) {}
		
		return "Error";
	}
	

	
	public void setDateOfJoin(Calendar dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}
	
}
