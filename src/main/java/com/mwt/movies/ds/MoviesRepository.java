package com.mwt.movies.ds;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.mwt.movies.ds.entities.Movie;

/**
 * Repository for Movie data implemented using Spring Data JPA.
 * 
 * @author v.manea
 */
public interface MoviesRepository extends Repository<Movie, Long> {
	/**
	 * Find an account with the specified account number.
	 *
	 * @param accountNumber
	 * @return The account if found, null otherwise.
	 */
	public Movie findByNumber(String accountNumber);
	
	/**
	 * Find account with specific username
	 * 
	 * @param username
	 * @return the Movie which has that username
	 */
	public Movie findByUsername(String username);
	
	/**
	 * 
	 */
	@Query("SELECT count(*) from Movie where username = ?1 and password = ?2")
	public int findByUsernameAndPassword(String usr, String psw);

	/**
	 * Fetch the number of accounts known to the system.
	 * 
	 * @return The number of accounts.
	 */
	@Query("SELECT count(*) from Movie")
	public int countAccounts();
}
