package com.mwt.security.monolit;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.mwt.login.filter.JWTAuthenticationFilter;
import com.mwt.security.api.JWTAuthorizationFilter;
import com.mwt.security.login.LoginMicroserviceSecurityConfig;

import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;

/**
 * Configuration class that enables security for the application when running as a monolit
 * 
 * @author v.manea
 *
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class MonolitSecurityConfig extends WebSecurityConfigurerAdapter {

	protected Logger logger = Logger.getLogger(LoginMicroserviceSecurityConfig.class.getName());

	private UserDetailsService userDetailsService;

	public MonolitSecurityConfig(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.addFilter(new JWTAuthorizationFilter(authenticationManager()))
				.addFilter(new JWTAuthenticationFilter(authenticationManager(), userDetailsService))
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
}