package com.mwt.login.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mwt.login.ds.UserRepository;
import com.mwt.login.ds.entities.User;

import static java.util.Collections.emptyList;

/**
 * Service that accomplishes login
 * 
 * @author v.manea
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository applicationUserRepository) {
		this.userRepository = applicationUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User applicationUser = userRepository.findByUsername(username);
		
		if (applicationUser == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new org.springframework.security.core.userdetails.User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
	}
}