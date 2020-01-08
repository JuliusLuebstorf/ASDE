package asde.proj4.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import asde.proj4.security.domain.UserPlayer;

public interface UserPlayerDAO extends JpaRepository<UserPlayer, Integer>{
	
	UserPlayer findByUsername(String nombre);
	
	UserPlayer findByEmail(String email);
}
