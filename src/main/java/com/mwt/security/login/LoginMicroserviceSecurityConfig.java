package com.mwt.security.login;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.mwt.login.filter.JWTAuthenticationFilter;

import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;

/**
 * Configuration class that adds the /login endpoint by enabling the ootb Spring
 * functionality
 * 
 * @author v.manea
 *
 */
@EnableWebSecurity
public class LoginMicroserviceSecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;

	protected Logger logger = Logger.getLogger(LoginMicroserviceSecurityConfig.class.getName());

	public LoginMicroserviceSecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.addFilter(new JWTAuthenticationFilter(authenticationManager(), userDetailsService))
				// this disables session creation on Spring Security
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
}