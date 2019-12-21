package asde.proj4.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import asde.proj4.persistence.model.Game;
import asde.proj4.services.LoginService;


// page for create a projetc https://start.spring.io/

@RestController
public class Controller {

	@Autowired
	private LoginService loginService;

	@Autowired
	private Game game;
	
	

	// @RequestMapping(value="/", method = RequestMethod.GET)
	@GetMapping("/hello")
	@ResponseBody
	public String showMessage() {
		return "<h1>Hello Word with Spring</h1>";
	}

	@GetMapping("/login")
	@ResponseBody
	public String login(@RequestParam String username, @RequestParam String password) {
		// http://localhost:8080/login?username=a&password

		if (username == null || username.isEmpty())
			return "<h1 style='color:red'>Username can not be empty</h1>";

		if (loginService.login(username, password))
			return "<h1>Success</h1>";

		return "<h1 style='color:red'>Failed</h1>";
	}


	@GetMapping("/")
	public String showJSP() {
		return "index";
	}

	

	@CrossOrigin
	@GetMapping("/addUser")
	public String addUser(@RequestParam String name, @RequestParam String pass, @RequestParam int coin) {
		// http://localhost:8080/login?username=a&password

		if (name == null || name.isEmpty())
			return "<h1 style='color:red'>Username can not be empty</h1>";

		// if(loginService.login(name, pass)) {
		//game.addPlayer(name, pass, coin);

		return "<h1>Success</h1>";
		// }

		// return "<h1 style='color:red'>Failed</h1>";
	}
	
	
}
