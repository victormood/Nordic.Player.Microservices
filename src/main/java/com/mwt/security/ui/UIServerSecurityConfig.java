package com.mwt.security.ui;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;

/**
 * Configuration class that allows access to server resources
 * 
 * @author v.manea
 *
 */
@EnableWebSecurity
public class UIServerSecurityConfig extends WebSecurityConfigurerAdapter {

	protected Logger logger = Logger.getLogger(UIServerSecurityConfig.class.getName());

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("UIServerSecurityConfig init");
		http.cors().and().csrf().disable()
				.authorizeRequests().antMatchers("/", "/js/**", "/css/**", "/modules/authentication/**").permitAll()
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