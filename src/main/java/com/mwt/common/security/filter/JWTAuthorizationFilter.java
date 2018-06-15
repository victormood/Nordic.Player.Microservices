package com.mwt.common.security.filter;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.mwt.common.constants.SecurityConstants.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * This filter should be part of every microservice chain call in order each
 * microservice to be able to detect if an unauthorized call is made through
 * them
 * 
 * @author v.manea
 *
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	protected Logger logger;

	public JWTAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);

		logger = Logger.getLogger(getClass().getName());
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(AUTHORIZATION_HEADER_NAME);
		
		logger.info(AUTHORIZATION_HEADER_NAME + "=" + header);

		if (header == null || !header.startsWith(TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(AUTHORIZATION_HEADER_NAME);
		
		if (token != null) {
			// parse the token.
			String user = Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody().getSubject();

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}
			
			return null;
		}
		return null;
	}
}