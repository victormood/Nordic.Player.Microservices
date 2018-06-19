package com.mwt.login.ds;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.mwt.login.ds.entities.User;

/**
 * Repository for User data implemented using Spring Data JPA.
 * 
 * @author v.manea
 */
public interface UserRepository extends Repository<User, Long> {
	
	/**
	 * Find account with specific username
	 * 
	 * @param username
	 * @return the User which has that username
	 */
	public User findByUsername(String username);

	/**
	 * Fetch the number of accounts known to the system.
	 * 
	 * @return The number of accounts.
	 */
	@Query("SELECT count(*) from User")
	public int countUsers();
}
