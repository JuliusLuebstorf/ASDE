package asde.proj4.security.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import asde.proj4.security.config.JwtUtil;
import asde.proj4.security.dao.UserPlayerDAO;
import asde.proj4.security.domain.UserPlayer;
import asde.proj4.security.service.SendEmail;
import asde.proj4.security.service.UserService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class UserController {

	@Autowired
	private UserPlayerDAO userDAO;

	@Autowired
	private UserService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Autowired
	private SendEmail sendEmail;

	@Autowired // @Resource(name="authenticationManager")
	private AuthenticationManager authenticationManager;

	@CrossOrigin
	@GetMapping("/users")
	public List<UserPlayer> users() {
		List<UserPlayer> users = userDAO.findAll();
		return users;
	}

	@CrossOrigin
	@PostMapping("/perform_login")
	public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) throws Exception {

		UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(username, password);
		Authentication auth = authenticationManager.authenticate(authReq);
		SecurityContext sc = SecurityContextHolder.getContext();
		sc.setAuthentication(auth);
		HttpSession session = request.getSession(true);
		session.setAttribute("SPRING_SECURITY_CONTEXT", sc);
		
		if(sc.getAuthentication().isAuthenticated()) {
			
			String token = JwtUtil.makeToken(username);
			//return token;
			return new ResponseEntity<String>(token, HttpStatus.OK); //200
		}

		return new ResponseEntity<Error>(HttpStatus.UNAUTHORIZED); //401
	}

	@CrossOrigin
	@PostMapping("/addUser")
	public ResponseEntity<?> add(@RequestParam String username, @RequestParam String pass, @RequestParam String email,
			HttpServletResponse httpResponse) throws Exception {

		try {
			UserPlayer user = userDAO.findByUsernameOrEmail(username, email);

			if (user != null) {
				// httpResponse.sendRedirect("http://127.0.0.1:3000/addUser?msg=addUser_problem");
				return new ResponseEntity<Error>(HttpStatus.CONFLICT); //409 error el usuario o el email ya existen
			}

			UserPlayer us = new UserPlayer(username, bcrypt.encode(pass), email);

			if (userDAO.save(us) != null)
				return new ResponseEntity<>(HttpStatus.OK); //200

			// httpResponse.sendRedirect("http://127.0.0.1:3000/login");

		} catch (Exception e) {
			// httpResponse.sendRedirect("http://127.0.0.1:3000/addUser?msg=addUser_problem");
		}

		return new ResponseEntity<Error>(HttpStatus.INTERNAL_SERVER_ERROR); //500
		
		
	}

	@CrossOrigin
	@PostMapping("/recoveryPass")
	public ResponseEntity<?> recoveryPass(@RequestParam String email, HttpServletResponse httpResponse) throws Exception {
		try {
			UserPlayer user = userDAO.findByEmail(email);

			if (user == null) {
				//httpResponse.sendRedirect("http://127.0.0.1:3000/recoveryPass?msg=email_incorrect");
				return new ResponseEntity<Error>(HttpStatus.NOT_FOUND); //404 user dont exist
			}

			String tempPass = System.currentTimeMillis() + "";
			tempPass = tempPass.substring(tempPass.length() - 5);

			user.setPass(bcrypt.encode(tempPass));
			userDAO.save(user);

			String subject = "Password recovery process";
			String content = "Please visit our site http://127.0.0.1:3000/updatePass for recovery your password, use this temporal password: "
					+ tempPass;
			sendEmail.sendEmail(email, subject, content);

			//httpResponse.sendRedirect("http://127.0.0.1:3000/updatePass");
			return new ResponseEntity<>(HttpStatus.OK); //200
			
		} catch (Exception e) {

			e.printStackTrace();

			//httpResponse.sendRedirect("http://127.0.0.1:3000/recoveryPass?msg=problem");
			return new ResponseEntity<Error>(HttpStatus.INTERNAL_SERVER_ERROR); //500
		}

	}

	@CrossOrigin
	@GetMapping("/updatePass")
	public ResponseEntity<?> changePass(@RequestParam String username, @RequestParam String oldPass,
			@RequestParam String newPass) {
		try {
			UserPlayer user = userDAO.findByUsername(username);

			
			if (bcrypt.matches(oldPass, user.getPass())) {

				user.setPass(bcrypt.encode(newPass));

				userDAO.save(user);

				return new ResponseEntity<>(HttpStatus.OK); //200
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return new ResponseEntity<Error>(HttpStatus.INTERNAL_SERVER_ERROR); //500
	}

	@CrossOrigin
	@GetMapping("/currentUserName")
	@ResponseBody
	public ResponseEntity<?> currentUserName(HttpServletRequest request) {
		
		String username=JwtUtil.getUsernameFromToken(request);
		
		if(!username.equals("")) {
			
			return new ResponseEntity<String>(username, HttpStatus.OK); //200
		}
		
		return new ResponseEntity<Error>(HttpStatus.NOT_FOUND); //404 user dont exist
	}

	@CrossOrigin
	@PostMapping("/homepage")
	public void homepage(HttpServletResponse httpResponse) throws Exception {
		/*
		 * Object principal =
		 * SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 * 
		 * String username = ""; if (principal instanceof UserDetails) {
		 * 
		 * username = ((UserDetails) principal).getUsername();
		 * 
		 * } else {
		 * 
		 * username = principal.toString();
		 * 
		 * }
		 */

		/*
		 * String jwt = getJWTToken(username);
		 * 
		 * httpResponse.setContentType("application/json");
		 * httpResponse.setCharacterEncoding("UTF-8");
		 * httpResponse.setStatus(HttpStatus.CREATED.value());
		 * httpResponse.setHeader(TOKEN_HEADER, TOKEN_PREFIX + jwt);
		 */

		// httpResponse.sendRedirect("http://127.0.0.1:3000/homepage?user=" + username);

	}

}