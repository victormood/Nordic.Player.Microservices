package com.mwt.server.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

import static com.mwt.common.constants.ServerNameConstants.*;
import static com.mwt.common.constants.SystemPropertyConstants.*;

/**
 * The interface module
 * 
 * @author v.manea
 */
@EnableAutoConfiguration
@Import(UIServerConfiguration.class)
public class UIServer {

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for web-server.properties or web-server.yml
		System.setProperty(SPRING_CONFIG_NAME, UI_SERVER);
		SpringApplication.run(UIServer.class, args);
	}
}
