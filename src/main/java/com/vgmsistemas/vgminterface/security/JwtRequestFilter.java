package com.vgmsistemas.vgminterface.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.vgmsistemas.vgminterface.service.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	private static Logger LOG =  LoggerFactory.getLogger(JwtRequestFilter.class)	;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		String requestTokenHeader; 
		
		LOG.info("X-Auth-Token: " + request.getHeader("X-Auth-Token"));
		
		/*= request.getHeader("Authorization");
		if (requestTokenHeader == null) {*/
			requestTokenHeader = request.getHeader("X-Auth-Token");
			LOG.info("X-Auth-Token: " + requestTokenHeader);
		/*}*/
		
		String username = null;
		String jwtToken = null;
		
		//JWT Token tiene la forma de "Bearer token". Elimina la palabra Bearer y obtén solo el Token
		if (requestTokenHeader != null) {
			if (requestTokenHeader.startsWith("Bearer ")) {
				jwtToken = requestTokenHeader.substring(7);
			} else {
				jwtToken = requestTokenHeader;
			}
			
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				//System.out.println("No se puede obtener el token");
				LOG.error("No se puede obtener X-Auth-Token. " + e.getMessage());
			} catch (ExpiredJwtException e) {
				//System.out.println("El token expiró");
				LOG.error("El Token expiró " + e.getMessage());
			}
		} else {
			//logger.warn("El token no se encuentra");
			LOG.warn("El header X-Auth-Token no existe " );
		}

		// Once we get the token validate it.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

			// if token is valid configure Spring Security to manually set
			// authentication
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		chain.doFilter(request, response);
	}

}