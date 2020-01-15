package asde.proj4.security.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

public class JwtUtil {

	public static String KEYSECRET = "laclavesecreta";

	public static String makeToken(String username) {
		String token = Jwts.builder().setSubject(username)
				// Vamos a asignar un tiempo de expiracion de 12 horas
				.setExpiration(new Date(System.currentTimeMillis() + 43200000))
				// Hash con el que firmaremos la clave
				.signWith(SignatureAlgorithm.HS512, KEYSECRET).compact();

		return token;
	}

	// Metodo para crear el JWT y enviarlo al cliente en el header de la respuesta
	static void addAuthentication(HttpServletResponse res, String username) {

		String token = makeToken(username);

		// agregamos al encabezado el token
		res.addHeader("Authorization", token);

		// res.setStatus(HttpServletResponse.SC_OK);
		try {
			res.getWriter().write(token);
			res.getWriter().flush();
			res.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getUsernameFromToken(HttpServletRequest request) {

		String token = request.getHeader("Authorization");

		// si hay un token presente, entonces lo validamos
		if (token != null) {
			String user = Jwts.parser().setSigningKey(KEYSECRET).parseClaimsJws(token) // este metodo es el que valida
					.getBody().getSubject();
			return user;
		}
		
		return "";
	}



	// Metodo para validar el token enviado por el cliente
	static Authentication getAuthentication(HttpServletRequest request, String[] roles) throws IOException {

		
		String user = getUsernameFromToken(request);

		
		if (user != null && user.length()>0) {
			// Recordamos que para las demas peticiones que no sean /login
			// no requerimos una autenticacion por username/password
			// por este motivo podemos devolver un UsernamePasswordAuthenticationToken sin
			// password, pero debemos consultar nuevamente los roles
			// seguramente hay una mejor forma de implementar roles, pero no esta del todo
			// mal volver a consultarlos, puesto que si hubo un cambio
			// en la asignación de roles a un usuario, se le otorgara o se le denegara el
			// acceso de forma automatica, y no hay que esperar al vencimiento del token

			Authentication result = user != null
					? new UsernamePasswordAuthenticationToken(user, null, AuthorityUtils.createAuthorityList(roles))
					: null;

			return result;
		}

		return null;
	}

}