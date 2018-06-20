package com.mwt.login.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mwt.login.ds.entities.User;
import com.mwt.login.service.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.mwt.common.constants.SecurityConstants.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	protected Logger logger = Logger.getLogger(JWTAuthenticationFilter.class.getName());

	private AuthenticationManager authenticationManager;
	private UserDetailsServiceImpl userDetailsService;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
		this.authenticationManager = authenticationManager;
		this.userDetailsService = (UserDetailsServiceImpl) userDetailsService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {
			User creds = new ObjectMapper().readValue(req.getInputStream(), User.class);

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(),
					creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String login = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();
		Claims claims = Jwts.claims().setSubject(login);

		if (login != null && login.length() > 0) {
			if (userDetailsService != null) {
				claims.put("role", userDetailsService.getUserRoleByUsername(login));
			} else {
				logger.error("userDetailsService is null !");
			}
		}
		// Now we can generate the token
		String token = Jwts.builder().setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();

		res.addHeader(AUTHORIZATION_HEADER_NAME, TOKEN_PREFIX + token);

		// This one will allow JS to read the Authorization headers
		res.addHeader(EXPOSE_AUTH_HEADER_NAME, EXPOSE_AUTH_HEADER_VALUE);
	}
}