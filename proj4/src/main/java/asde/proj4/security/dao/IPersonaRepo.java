package asde.proj4.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import asde.proj4.security.domain.Persona;

public interface IPersonaRepo extends JpaRepository<Persona, Integer>{
	
}
