package com.mwt.login.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mwt.movies.ds.MoviesRepository;
import com.mwt.movies.ds.entities.Movie;

import static java.util.Collections.emptyList;

/**
 * Service that accomplishes login
 * 
 * @author v.manea
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private MoviesRepository moviesRepository;

	public UserDetailsServiceImpl(MoviesRepository applicationUserRepository) {
		this.moviesRepository = applicationUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Movie applicationUser = moviesRepository.findByUsername(username);
		
		if (applicationUser == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
	}
}