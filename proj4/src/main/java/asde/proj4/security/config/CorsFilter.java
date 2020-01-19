package asde.proj4.security.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CorsFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",  "Accept, Content-Type, Origin, Authorization, X-Auth-Token"); //"authorization, content-type, xsrf-token"
        response.addHeader("Access-Control-Expose-Headers", "authorization, content-type, xsrf-token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        
        
       
        
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else { 
            filterChain.doFilter(request, response);
        }
    	
    	/*HttpServletRequest oRequest = (HttpServletRequest) request;
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                " authorization, content-type, xsrf-token, Origin, X-Requested-With, Content-Type, Accept,AUTH-TOKEN");
        response.addHeader("Access-Control-Expose-Headers", "xsrf-token");
        if (oRequest.getMethod().equals("OPTIONS")) {
            response.flushBuffer();
        } else {
        	filterChain.doFilter(request, response);
        }*/
    }

}