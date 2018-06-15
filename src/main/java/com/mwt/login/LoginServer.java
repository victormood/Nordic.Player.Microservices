package com.mwt.login;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.mwt.accounts.ds.AccountRepository;

import static com.mwt.common.constants.SystemPropertyConstants.*;
import static com.mwt.common.constants.ServerNameConstants.*;

/**
 * Run as a micro-service
 * <p>
 * Note that the configuration for this application is imported from
 * {@link WebServerConfiguration}. This is a deliberate separation of concerns.
 * 
 * @author v.manea
 */
@EnableAutoConfiguration
@Import(LoginConfiguration.class)
public class LoginServer {

	@Autowired
	protected AccountRepository accountRepository;

	protected Logger logger = Logger.getLogger(LoginServer.class.getName());

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for accounts-server.properties or accounts-server.yml
		System.setProperty(SPRING_CONFIG_NAME, LOGIN_SERVER);

		SpringApplication.run(LoginServer.class, args);
	}
}
