package asde.proj4.data.persistence.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asde.proj4.data.persistence.model.Game;


@Repository
public interface GameDAO extends CrudRepository<Game, Integer>{

	List<Game> findByName(String name);
	
}
