package com.unical.website.asde;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.unical.website.asde.dao.PlayerDAO;
import com.unical.website.asde.model.Player;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private PlayerDAO repository;
	
	@Test
	public void contextLoads() {
		
		repository.save(new Player("pp", "pp", "pp", Calendar.getInstance()));
		
		assertEquals(1, repository.count());
		
		repository.deleteAll();
	}

}
