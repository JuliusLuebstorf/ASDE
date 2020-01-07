package asde.proj4.security.config.dao;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import asde.proj4.security.config.model.Player;



@Repository
public interface PlayerDAO extends CrudRepository<Player, String>{

	List<Player> findByName(String name);
	List<Player> findByNameGreaterThanOrderByNameDesc(String name);
	
	
	@Query("FROM Player p where p.dateOfJoin = :dateOfJoin AND coin > :coin ")
	List<Player> findByDateOfJoinWithCoinsGreater(Calendar dateOfJoin, int coin);
}
