package com.mwt.common.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.mwt.common.security.filter.JWTAuthorizationFilter;
import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

/**
 * Configures Spring security to permit only authenticated requests
 * 
 * @author v.manea
 *
 */
@EnableWebSecurity
@Order(2)
public class BasicMicroserviceSecurityConfig extends WebSecurityConfigurerAdapter {

	protected Logger logger = Logger.getLogger(BasicMicroserviceSecurityConfig.class.getName());

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.authorizeRequests().anyRequest().authenticated()
				.and()
				.addFilter(new JWTAuthorizationFilter(authenticationManager()))
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