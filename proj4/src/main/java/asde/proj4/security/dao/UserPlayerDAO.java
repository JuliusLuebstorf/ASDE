package asde.proj4.security.dao;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import asde.proj4.security.domain.UserPlayer;
import asde.proj4.security.domain.UserPlayerSimple;

public interface UserPlayerDAO extends JpaRepository<UserPlayer, Integer>{
	
	UserPlayer findByUsername(String username);
	
	UserPlayer findByEmail(String email);
	
	UserPlayer findByUsernameOrEmail(String username, String email);
	
	List<UserPlayer> findTop3ByOrderByTicTacToeScoreDesc();
	
	
	//@Query(value= "SELECT username, ticTacToeScore FROM UserPlayer ORDER BY ticTacToeScore DESC LIMIT 3", nativeQuery = true)
	//List<UserPlayerSimple> findTop3WithQuery(/*Specification<UserPlayer> spec*/);
	
	@Query(value= "SELECT * FROM UserPlayer ORDER BY ticTacToeScore DESC LIMIT 3", nativeQuery = true)
	List<UserPlayer> findTop3WithQuery();
	
	@Modifying
	@Query(value= "UPDATE USERPLAYER SET TICTACTOESCORE = 0", nativeQuery = true)
	void updateAllScoreToZero();
}
