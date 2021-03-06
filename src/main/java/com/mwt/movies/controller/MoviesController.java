package com.mwt.movies.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping(value = "/movies")
public class MoviesController {

	protected Logger logger = Logger.getLogger(MoviesController.class.getName());
	
	protected MoviesRepository moviesRepository;
	
	@RequestMapping(value = "/list")
	@PreAuthorize("hasRole('MOVIE') or hasRole('ADMIN')")
	public List<Movie> findAll() {
		List<Movie> movies = moviesRepository.findAll();

		return movies;
	}

	/**
	 * Create an instance plugging in the respository of Movies.
	 * 
	 * @param moviesRepository
	 *            An account repository implementation.
	 */
	@Autowired
	public MoviesController(MoviesRepository moviesRepository) {
		this.moviesRepository = moviesRepository;

		logger.info("MovieRepository says system has " + moviesRepository.countMovies() + " movies");
	}
}
