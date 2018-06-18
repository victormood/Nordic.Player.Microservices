package com.mwt.ui.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

/**
 * Configuration class that allows access to server resources
 * 
 * @author v.manea
 *
 */
@EnableWebSecurity
@Order(3)
public class UIServerSecurityConfig extends WebSecurityConfigurerAdapter {

	protected Logger logger = Logger.getLogger(UIServerSecurityConfig.class.getName());

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.authorizeRequests().antMatchers("/", "/js/**", "/css/**", "/modules/**").permitAll()
				.and()
				// this disables session creation on Spring Security
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
}