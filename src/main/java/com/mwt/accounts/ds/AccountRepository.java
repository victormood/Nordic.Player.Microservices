package com.mwt.accounts.ds;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.mwt.accounts.ds.entities.Account;

/**
 * Repository for Account data implemented using Spring Data JPA.
 * 
 * @author v.manea
 */
public interface AccountRepository extends Repository<Account, Long> {
	/**
	 * Find an account with the specified account number.
	 *
	 * @param accountNumber
	 * @return The account if found, null otherwise.
	 */
	public Account findByNumber(String accountNumber);
	
	/**
	 * Find account with specific username
	 * 
	 * @param username
	 * @return the Account which has that username
	 */
	public Account findByUsername(String username);
	
	/**
	 * 
	 */
	@Query("SELECT count(*) from Account where username = ?1 and password = ?2")
	public int findByUsernameAndPassword(String usr, String psw);

	/**
	 * Fetch the number of accounts known to the system.
	 * 
	 * @return The number of accounts.
	 */
	@Query("SELECT count(*) from Account")
	public int countAccounts();
}
