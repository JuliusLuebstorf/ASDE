package asde.proj4;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import asde.proj4.security.config.dao.PlayerDAO;
import asde.proj4.security.config.model.Player;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
	@Autowired
	private PlayerDAO repository;
	
	@Test
	public void contextLoads() {
		
		repository.save(new Player("pp", "pp", 200));
		
		assertEquals(1, repository.count());
		//assertEquals(200, repository.findByNameGreaterThanOrderByNameDesc("pp").get(0).getCoin());
		
		repository.deleteAll();
	}
}
