package com.mwt.movies.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mwt.movies.ds.MoviesRepository;
import com.mwt.movies.ds.entities.Movie;

/**
 * A RESTFul controller for accessing movie information.
 * 
 * @author v.manea
 */
@RestController
public class MoviesController {

	protected Logger logger = Logger.getLogger(MoviesController.class.getName());
	protected MoviesRepository moviesRepository;

	/**
	 * Create an instance plugging in the respository of Accounts.
	 * 
	 * @param moviesRepository
	 *            An account repository implementation.
	 */
	@Autowired
	public MoviesController(MoviesRepository moviesRepository) {
		this.moviesRepository = moviesRepository;

		logger.info("MoviesRepository says system has " + moviesRepository.countAccounts() + " accounts");
	}

	/**
	 * Fetch an account with the specified account number.
	 * 
	 * @param accountNumber
	 *            A numeric, 9 digit account number.
	 * @return The account if found.
	 * @throws AccountNotFoundException
	 *             If the number is not recognised.
	 */
	@RequestMapping(value = "/accounts/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Movie byNumber(@PathVariable("accountNumber") String accountNumber) {

		logger.info("accounts-service byNumber() invoked: " + accountNumber);
		Movie movie = moviesRepository.findByNumber(accountNumber);
		logger.info("accounts-service byNumber() found: " + movie);

		if (movie == null)
			return null;
		else {
			return movie;
		}
	}
}
