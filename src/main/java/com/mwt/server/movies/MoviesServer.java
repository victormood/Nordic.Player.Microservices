package com.mwt.server.movies;

import static com.mwt.common.constants.ServerNameConstants.*;
import static com.mwt.common.constants.SystemPropertyConstants.SPRING_CONFIG_NAME;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import com.mwt.movies.ds.MoviesRepository;

/**
 * "Movies" microservice starting point - this will start an embedded server running
 * "Movies" server
 * 
 * @author v.manea
 */
@EnableAutoConfiguration
@ComponentScan({"com.mwt.movies", "com.mwt.common", "com.mwt.security.api"})
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
		// Tell server to look for movies-server.properties or movies-server.yml
		System.setProperty(SPRING_CONFIG_NAME, MOVIES_SERVER);

		SpringApplication.run(MoviesServer.class, args);
	}
}
