package asde.proj4;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import asde.proj4.security.dao.UserPlayerDAO;
import asde.proj4.security.domain.UserPlayer;



@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
	@Autowired
	private UserPlayerDAO repository;
	
	@Test
	public void contextLoads() {
		
		repository.save(new UserPlayer("pp", "pp", "pp"));
		
		;
		
		assertEquals("pp", repository.findByUsernameOrEmail("aa", "pp").getUsername());
		
		assertEquals("pp", repository.findByUsernameOrEmail("pp", "aa").getUsername());
		//assertEquals(200, repository.findByNameGreaterThanOrderByNameDesc("pp").get(0).getCoin());
		
		assertEquals("pp", repository.findByUsernameOrEmail("aa", "aa").getUsername());
		
		repository.deleteAll();
	}
}
