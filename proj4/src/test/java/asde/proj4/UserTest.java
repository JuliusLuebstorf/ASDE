package asde.proj4;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import asde.proj4.security.dao.UserPlayerDAO;
import asde.proj4.security.domain.UserPlayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
	@Autowired
	private UserPlayerDAO repository;
	
	/*
	@Test
	public void contextLoads() {
		
		repository.save(new UserPlayer("pp", "pp", "pp"));
		
		assertEquals("pp", repository.findByUsernameOrEmail("aa", "pp").getUsername());
		
		assertEquals("pp", repository.findByUsernameOrEmail("pp", "aa").getUsername());
		//assertEquals(200, repository.findByNameGreaterThanOrderByNameDesc("pp").get(0).getCoin());
		
		assertEquals("pp", repository.findByUsernameOrEmail("aa", "aa").getUsername());
		
		repository.deleteAll();
	}*/
	
	
	@Test
	public void loging() {
		
		String user = "";
		try {

			URL url = new URL("http://localhost:8080/perform_login");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			String input = "{\"username\":\"a\",\"password\":\"a\"}";

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

	                String token = conn.getHeaderField("Authorization");

	                System.out.println(token.trim());

			conn.disconnect();

	                url = new URL("http://localhost:8080/currentUserName");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
	                conn.setRequestProperty("Authorization", token);
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				user += output;
				System.out.println(output);
			}

			
			
			conn.disconnect();                

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		 }
		
		
		
		assertEquals("a", user);
		
		
	}
}
