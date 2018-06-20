package com.mwt.server.login;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import com.mwt.login.ds.UserRepository;
import static com.mwt.common.constants.SystemPropertyConstants.*;
import static com.mwt.common.constants.ServerNameConstants.*;

/**
 * Login microservice starting point - this will start an embeded server running
 * Login server
 * 
 * @author v.manea
 */
@EnableAutoConfiguration
@ComponentScan({ "com.mwt.login", "com.mwt.security.login" })
public class LoginServer {

	@Autowired
	protected UserRepository userRepository;

	protected Logger logger = Logger.getLogger(LoginServer.class.getName());

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for login-server.properties or login-server.yml
		System.setProperty(SPRING_CONFIG_NAME, LOGIN_SERVER);

		SpringApplication.run(LoginServer.class, args);
	}
}
