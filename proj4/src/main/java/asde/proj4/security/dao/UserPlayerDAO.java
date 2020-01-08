package asde.proj4.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import asde.proj4.security.domain.UserPlayer;

public interface UserPlayerDAO extends JpaRepository<UserPlayer, Integer>{
	
	UserPlayer findByUsername(String username);
	
	UserPlayer findByEmail(String email);
	
	UserPlayer findByUsernameOrEmail(String username, String email);
}
