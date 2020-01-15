package asde.proj4.security.config;

import java.util.Arrays;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
	
	@Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	/*http.csrf().disable()
		  .authorizeRequests()  
          .antMatchers("/addUser").permitAll()
          .antMatchers("/updatePass").permitAll()
          .antMatchers("/recoveryPass").permitAll()
          //.antMatchers("/currentUserName").permitAll()
          .antMatchers("/index").permitAll()
          .anyRequest().authenticated()   //.authenticated()  .permitAll()
          .and()
          .formLogin().loginPage("http://localhost:3000/index")
          .loginProcessingUrl("/perform_login")
          //.defaultSuccessUrl("/homepage")
          .defaultSuccessUrl("http://localhost:3000/homepage",true)
          .failureUrl("http://localhost:3000/index?msg=login_incorrect")
          .and()
       // Las peticiones /login pasaran previamente por este filtro
          .addFilterBefore(new LoginFilter("/login", authenticationManager()),
                  UsernamePasswordAuthenticationFilter.class)                
          // Las demás peticiones pasarán por este filtro para validar el token
          .addFilterBefore(new JwtFilter(),
                  UsernamePasswordAuthenticationFilter.class)
          .logout().permitAll().and()
          .httpBasic();*/
    	
    	http.csrf().disable().authorizeRequests()
        .antMatchers("/perform_login").permitAll() //permitimos el acceso a /login a cualquiera
        .antMatchers("/addUser").permitAll()
        .antMatchers("/recoveryPass").permitAll() 
        .antMatchers("/updatePass").permitAll()
        //.antMatchers("smtp.gmail.com").permitAll()
        
        .anyRequest().authenticated()//authenticated() //cualquier otra peticion requiere autenticacion
        /*.and()
        .formLogin().loginPage("http://localhost:3000/index")
        .loginProcessingUrl("/perform_login")*/
        //.defaultSuccessUrl("/homepage")
        //.defaultSuccessUrl("http://localhost:3000/homepage",true)
        //.failureUrl("http://localhost:3000/index?msg=login_incorrect")
        .and()
        .addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class)
        // Las peticiones /login pasaran previamente por este filtro
       /*.addFilterBefore(new LoginFilter("/perform_login", authenticationManager()),
                UsernamePasswordAuthenticationFilter.class)        */      
        // Las demás peticiones pasarán por este filtro para validar el token
        .addFilterBefore(new JwtFilter(),
                UsernamePasswordAuthenticationFilter.class)
        
        .logout().permitAll().and()
        .httpBasic();
    	
    	//http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
    	
    }
    


    @Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

    	auth.userDetailsService(userDetailsService).passwordEncoder(bcrypt); 	 

	}
    
    
    
    
}
