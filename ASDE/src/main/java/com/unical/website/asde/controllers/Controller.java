package com.unical.website.asde.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.unical.website.asde.model.Game;
import com.unical.website.asde.model.Player;
import com.unical.website.asde.services.LogingService;

// page for create a projetc https://start.spring.io/

@RestController
public class Controller {

	@Autowired
	private LogingService loginService;

	

	// @RequestMapping(value="/", method = RequestMethod.GET)
	@GetMapping("/hello")
	@ResponseBody
	public String showMessage() {
		return "<h1>Hello Word with Spring</h1>";
	}

	@GetMapping("/login")
	@ResponseBody
	public Player login(@RequestParam String email, @RequestParam String pass) {
		// http://localhost:8080/login?username=a&password

		return loginService.login(email, pass);
		
		/*if (email == null || email.isEmpty())
			return "<h1 style='color:red'>Username can not be empty</h1>";

		if (loginService.login(email, pass)!=null)
			return "<h1>Success</h1>";

		return "<h1 style='color:red'>Failed</h1>";*/
	}



	@CrossOrigin
	@GetMapping("/register")
	public String register(@RequestParam String name, @RequestParam String email, @RequestParam String pass) {
		return loginService.register(name, email, pass);
	}
	
	
	@CrossOrigin
	@GetMapping("/logout")
	public boolean logout(@RequestParam String email) {
		return loginService.logout(email);
	}
	
	
	
	@GetMapping("/")
	public String showJSP() {
		return "index";
	}
	
}
