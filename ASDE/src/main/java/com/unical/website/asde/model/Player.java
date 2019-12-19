package com.unical.website.asde.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Player {

	@OneToMany(mappedBy = "player")
	private Set<RatingTable> ratings;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String pass;
	
	@Column(nullable = false)
	private Calendar dateOfJoin;
	
	
	
	
	
	public Player() {
		super();
	}
	
	
	
	
	public Player( String name, String email, String pass, Calendar dateOfJoin) {
		super();
		this.name = name;
		this.email = email;
		this.pass = pass;
		this.dateOfJoin = dateOfJoin;
	}


	


	public int getId() {
		return id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getPass() {
		return pass;
	}




	public void setPass(String pass) {
		this.pass = pass;
	}




	public Calendar getDateOfJoin() {
		return dateOfJoin;
	}




	public void setDateOfJoin(Calendar dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}




	public String getDateOfJoinString() {
		
		
		
		try {
		SimpleDateFormat s = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.ENGLISH);
		return s.format(dateOfJoin.getTime());
		}
		catch(Exception e) {}
		
		return "Error";
	}
	

	
	
}
