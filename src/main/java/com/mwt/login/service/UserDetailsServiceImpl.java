package com.mwt.login.service;

import com.mwt.accounts.ds.AccountRepository;
import com.mwt.accounts.ds.entities.Account;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

/**
 * Service that accomplishes login
 * 
 * @author v.manea
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private AccountRepository accountRepository;

	public UserDetailsServiceImpl(AccountRepository applicationUserRepository) {
		this.accountRepository = applicationUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account applicationUser = accountRepository.findByUsername(username);
		
		if (applicationUser == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
	}
}