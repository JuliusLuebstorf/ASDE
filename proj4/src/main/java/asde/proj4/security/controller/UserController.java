package asde.proj4.security.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import asde.proj4.security.dao.UserPlayerDAO;
import asde.proj4.security.domain.UserPlayer;
import asde.proj4.security.service.SendEmail;
import asde.proj4.security.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserController {

	@Autowired
	private UserPlayerDAO userDAO;

	@Autowired
	private UserService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Autowired
	private SendEmail sendEmail;

	@GetMapping("/users")
	@ResponseBody
	public List<UserPlayer> users() {

		return userDAO.findAll();
	}

	@CrossOrigin
	@GetMapping("/addUser")
	@ResponseBody
	public boolean add(@RequestParam String username, @RequestParam String pass, @RequestParam String email) {

		try {
			UserPlayer us = new UserPlayer(username, bcrypt.encode(pass), email);

			userDAO.save(us);	
			
			return true;
			
		} catch (Exception e) {
			 
		}
		

		return false;
	}

	@CrossOrigin
	@GetMapping("/login2")
	@ResponseBody
	public boolean/* UserToken */ login(@RequestParam("username") String username, @RequestParam("pass") String pass) {

		try {
			UserDetails ud = userDetailsService.loadUserByUsername(username);
			return (ud.getPassword().equals(bcrypt.encode(pass)));
		} catch (Exception e) {

		}

		return false;

		/*
		 * String token = getJWTToken(username); UserToken user = new UserToken();
		 * user.setUsername(username); user.setToken(token); return user;
		 */

	}

	
	@CrossOrigin
	@GetMapping("/recoveryPass")
	@ResponseBody
	public boolean recoveryPass(@RequestParam String email) {
		try {
			UserPlayer user = userDAO.findByEmail(email);

			if(user == null) return false;
			
			String tempPass = System.currentTimeMillis()+"";
			tempPass = tempPass.substring(tempPass.length()-5);
			
			user.setPass(bcrypt.encode(tempPass));
			userDAO.save(user);
			
			String subject = "Password recovery process";
			String content = "Please visit our site http://127.0.0.1:3000/updatePass for recovery your password, use this temporal password: "+tempPass;
			sendEmail.sendEmail(email, subject, content);
			
		    return true;
			
		} catch (Exception e) {
           
			e.printStackTrace();
		}

		return false;
	}
	
	
	@CrossOrigin
	@GetMapping("/updatePass")
	@ResponseBody
	public boolean changePass(@RequestParam String username, @RequestParam String oldPass,
			@RequestParam String newPass) {
		try {
			UserPlayer user = userDAO.findByUsername(username);

			if (user.getPass().equals(bcrypt.encode(oldPass))) {

				user.setPass(bcrypt.encode(newPass));

				userDAO.save(user);

				return true;
			}
		} catch (Exception e) {
           
			e.printStackTrace();
		}

		return false;
	}

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts.builder().setId("softtekJWT").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}