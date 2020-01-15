package asde.proj4.security.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import asde.proj4.security.service.UserService;

@Configuration
@EnableWebSecurity
@Profile("!https")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bcrypt);
		
		/*auth.inMemoryAuthentication()
        .withUser("u").password(bcrypt.encode("u")).roles("USER")
        .and()
        .withUser("a").password(bcrypt.encode("a")).roles("ADMIN");*/
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable()
		.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
          .authorizeRequests()  
          .antMatchers("/addUser").permitAll()
          .antMatchers("/updatePass").permitAll()
          .antMatchers("/recoveryPass").permitAll()
          //.antMatchers("/currentUserName").permitAll()
          .antMatchers("/index").permitAll()
          .anyRequest().permitAll()   //.authenticated()
          .and()
          .formLogin().loginPage("http://localhost:3000/index")
          .loginProcessingUrl("/perform_login")
          .defaultSuccessUrl("/homepage")
          .failureUrl("http://localhost:3000/index?msg=login_incorrect")
          .and()
          .logout().permitAll().and()
          .httpBasic();
    	
    	
    }
}
