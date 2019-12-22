package com.unical.website.asde.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.unical.website.asde.dao.PlayerDAO;
import com.unical.website.asde.model.Player;

@Service
public class LogingService {

	@Autowired
	private PlayerDAO repository;
	
	private List<Player> userActive = new ArrayList<Player>();

	public Player login(@RequestParam String email, @RequestParam String pass) {
		// http://localhost:8080/login?pass=a&email=a

		if(email == null || email.isEmpty())
			return null;
		
		Player user = repository.findByEmail(email);
		
		if (user == null || !user.getPass().equals(pass))
			return null;

		logout(email);
		userActive.add(user);
		return user;
	}
	
	public boolean logout(@RequestParam String email) {
        //http://localhost:8080/logout?email=a
		
		if(email == null || email.isEmpty())
			return false;
		
		for (int i=0; i<userActive.size(); i++) {
			
			Player player = userActive.get(i);
			
			if(player.getEmail().equals(email)) {
				userActive.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	
	
	
	public String register(@RequestParam String name, @RequestParam String email, @RequestParam String pass) {
		//http://localhost:8080/register?name=a&pass=a&email=a
		
		String result;

		if(email == null || email.isEmpty())
			return "The email can not be empty";
		
		Player user = repository.findByEmail(email);
		
		if (user == null) {
			
			try {
				repository.save(new Player(name, email, pass));
				
				result = "User registered successfully";
			} catch (Exception e) {
				result = "Problem registering the user: "+e.getMessage();
			}
			
		}else
			result = "This email already has a asociate user";
		
			
		return result;
	}
	
}
