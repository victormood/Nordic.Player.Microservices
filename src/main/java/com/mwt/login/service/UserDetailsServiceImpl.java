package com.mwt.login.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mwt.login.ds.UserRepository;
import com.mwt.login.ds.entities.User;

import java.util.ArrayList;
import java.util.List;

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
	
	/**
	 * Returns the Role for a given username
	 * 
	 * @param username
	 * @return user's role
	 */
	public String getUserRoleByUsername(String username) {
		User applicationUser = userRepository.findByUsername(username);

		if (applicationUser != null) {
			return applicationUser.getRole();
		}
		
		return null;
	}

	/**
	 * Recovers the user from DB - username, password and role also
	 */
	@SuppressWarnings("serial")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User applicationUser = userRepository.findByUsername(username);

		if (applicationUser == null) {
			throw new UsernameNotFoundException(username);
		}

		return new org.springframework.security.core.userdetails.User(applicationUser.getUsername(),
				applicationUser.getPassword(), true, true, true, true,
				getGrantedAuthorities(new ArrayList<String>() {{add(applicationUser.getRole());}}));
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		
		return authorities;
	}
}