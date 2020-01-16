package asde.proj4.security.config;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Jwts;

@Controller
public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {

    	
    	// Obtenemos el token que viene en el encabezado de la peticion
        String token = ((HttpServletRequest) request).getHeader("Authorization");
       
        
        
        String[] list_roles = {};

        if (token != null) {
            String username = Jwts.parser()
                    .setSigningKey(JwtUtil.KEYSECRET) //la clave secreta esta declara en JwtUtil
                    .parseClaimsJws(token) //este metodo es el que valida
                    .getBody()
                    .getSubject();        //extraigo el nombre de usuario del token

	        
            
            //aqui se puede chequear si el username tiene permiso
            
            list_roles = new String[]{"USER"};
	
        }
        Authentication authentication = JwtUtil.getAuthentication((HttpServletRequest)request, list_roles);

        SecurityContextHolder.getContext().setAuthentication(authentication);

      
        
        filterChain.doFilter(request, response);

    }
}