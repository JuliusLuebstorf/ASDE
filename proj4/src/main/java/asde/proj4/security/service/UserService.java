package asde.proj4.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import asde.proj4.security.dao.UserPlayerDAO;
import asde.proj4.security.domain.UserPlayer;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserPlayerDAO repo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserPlayer us = repo.findByUsername(username);
		
		if(us == null) throw new UsernameNotFoundException("");
		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("USER"));
		
		UserDetails userDet = new User(us.getUsername(), us.getPass(), roles);
		
		return userDet;
	}

}
