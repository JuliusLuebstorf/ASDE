package asde.proj4.security.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public void add(@RequestParam String username, @RequestParam String pass, @RequestParam String email, HttpServletResponse httpResponse)throws Exception {

		try {
			UserPlayer user = userDAO.findByUsernameOrEmail(username, email);

			if(user != null) {
				httpResponse.sendRedirect("http://127.0.0.1:3000/addUser?msg=addUser_problem");
				return;
			}
			
			UserPlayer us = new UserPlayer(username, bcrypt.encode(pass), email);

			userDAO.save(us);	
			
			httpResponse.sendRedirect("http://127.0.0.1:3000/login");
			
		} catch (Exception e) {
			httpResponse.sendRedirect("http://127.0.0.1:3000/addUser?msg=addUser_problem");
		}
		

		
	}

	
	@CrossOrigin
	@GetMapping("/recoveryPass")
	public void recoveryPass(@RequestParam String email, HttpServletResponse httpResponse)throws Exception {
		try {
			UserPlayer user = userDAO.findByEmail(email);

			if(user == null) {
				httpResponse.sendRedirect("http://127.0.0.1:3000/recoveryPass?msg=email_incorrect");
				return;
			}
			
			String tempPass = System.currentTimeMillis()+"";
			tempPass = tempPass.substring(tempPass.length()-5);
			
			user.setPass(bcrypt.encode(tempPass));
			userDAO.save(user);
			
			String subject = "Password recovery process";
			String content = "Please visit our site http://127.0.0.1:3000/updatePass for recovery your password, use this temporal password: "+tempPass;
			sendEmail.sendEmail(email, subject, content);
			
			httpResponse.sendRedirect("http://127.0.0.1:3000/updatePass");
			
		} catch (Exception e) {
           
			e.printStackTrace();
			
			httpResponse.sendRedirect("http://127.0.0.1:3000/recoveryPass?msg=problem");
		}

	    
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
	
	@CrossOrigin
	@GetMapping("/currentUserName")
    @ResponseBody
    public String currentUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username = "";
		if (principal instanceof UserDetails) {

			username = ((UserDetails)principal).getUsername();

		} else {

		   username = principal.toString();

		}
		
        return username;
    }

	
	
	
	@CrossOrigin
	@GetMapping("/homepage")
	public void homepage(HttpServletResponse httpResponse)throws Exception {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username = "";
		if (principal instanceof UserDetails) {

			username = ((UserDetails)principal).getUsername();

		} else {

		   username = principal.toString();

		}
		
				httpResponse.sendRedirect("http://127.0.0.1:3000/homepage?user="+username);
			
	    
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