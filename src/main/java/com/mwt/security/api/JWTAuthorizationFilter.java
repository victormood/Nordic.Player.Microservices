package com.mwt.security.api;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.mwt.security.authorization.ApiGrantedAuthority;

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
			Claims claims = Jwts.parser().setSigningKey(SECRET.getBytes())
					.parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();

			// Extract data stored in token
			String username = claims.getSubject();
			String role = (String) claims.get("role");

			ArrayList<ApiGrantedAuthority> list = new ArrayList<>();
			ApiGrantedAuthority apiGA = new ApiGrantedAuthority(role);
			list.add(apiGA);

			// Return an Authenticated user with the list of Roles attached
			if (username != null) {
				return new UsernamePasswordAuthenticationToken(username, null, list);
			}
			
			return null;
		}
		
		return null;
	}
}