package com.unical.website.asde.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class LogingService {

	
	 
		public boolean login(@RequestParam String username, @RequestParam String  password) {
		//http://localhost:8080/login?username=a&password
	    	
	    	 if(username == null || username.equalsIgnoreCase("a"))
	    	return true;
	    	
	    	 
	    	return false;
		}
}
