package asde.proj4.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import asde.proj4.security.config.dao.PlayerDAO;

@Service
public class LogingService {

	
	@Autowired
	private PlayerDAO players;
	
		public boolean login(@RequestParam String username, @RequestParam String  password) {
		//http://localhost:8080/login?username=a&password
	    	
	    	 if(username == null || username.equalsIgnoreCase("a"))
	    	return true;
	    	
	    	 
	    	return false;
		}
}
