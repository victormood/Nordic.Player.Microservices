package com.mwt.movies.ds;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.mwt.movies.ds.entities.Movie;

/**
 * Repository for User data implemented using Spring Data JPA.
 * 
 * @author v.manea
 */
public interface MoviesRepository extends Repository<Movie, Long> {
	
	/**
	 * Find all movies
	 */
	public List<Movie> findAll();

	/**
	 * Fetch the number of movies known to the system.
	 * 
	 * @return The number of movies.
	 */
	@Query("SELECT count(*) from Movie")
	public int countMovies();
}
