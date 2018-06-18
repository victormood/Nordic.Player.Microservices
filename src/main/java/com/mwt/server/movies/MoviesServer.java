package com.mwt.server.movies;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.mwt.movies.ds.MoviesRepository;

/**
 * Runs as a micro-service
 * <p>
 * Note that the configuration for this application is imported from
 * {@link MoviesConfiguration}. This is a deliberate separation of concerns.
 * 
 * @author Victor Manea
 */
@EnableAutoConfiguration
@Import(MoviesConfiguration.class)
public class MoviesServer {

	@Autowired
	protected MoviesRepository moviesRepository;

	protected Logger logger = Logger.getLogger(MoviesServer.class.getName());

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for accounts-server.properties or
		// accounts-server.yml
		System.setProperty("spring.config.name", "accounts-server");

		SpringApplication.run(MoviesServer.class, args);
	}
}
