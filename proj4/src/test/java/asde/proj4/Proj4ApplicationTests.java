package asde.proj4;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import asde.proj4.persistence.dao.PlayerDAO;
import asde.proj4.persistence.model.Player;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Proj4ApplicationTests {

	@Autowired
	private PlayerDAO repository;
	
	@Test
	public void contextLoads() {
		
		repository.save(new Player("pp", "pp", "pp", Calendar.getInstance()));
		
		assertEquals(1, repository.count());
		
		repository.deleteAll();
	}

}
