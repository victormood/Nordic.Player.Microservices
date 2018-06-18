package com.mwt.server.localhost;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.mwt.movies.ds.MoviesRepository;

import static com.mwt.common.constants.SystemPropertyConstants.*;
import static com.mwt.common.constants.ServerNameConstants.*;

/**
 * Used for running all microservices on localhost
 * 
 * @author v.manea
 */
@EnableAutoConfiguration
@Import(LocalhostConfiguration.class)
public class LocalhostServer {

	@Autowired
	protected MoviesRepository moviesRepository;

	protected Logger logger = Logger.getLogger(LocalhostServer.class.getName());

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for accounts-server.properties or accounts-server.yml
		System.setProperty(SPRING_CONFIG_NAME, UI_SERVER);

		SpringApplication.run(LocalhostServer.class, args);
	}
}
