package asde.proj4.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import asde.proj4.security.config.dao.PlayerDAO;


// page for create a projetc https://start.spring.io/

@RestController
public class Controller {

	@Autowired
	private LogingService loginService;

	

	// @RequestMapping(value="/", method = RequestMethod.GET)
	@GetMapping("/hello")
	@ResponseBody
	public String showMessage() {
		return "<h1>Hello Worl with Spring</h1>";
	}

	
}
