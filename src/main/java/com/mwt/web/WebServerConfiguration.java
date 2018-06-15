package com.mwt.web;

import java.util.logging.Logger;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configurations for WebServer
 * 
 * @author v.manea
 */
@Configuration
@ComponentScan({"com.mwt.web"})
public class WebServerConfiguration {

	protected Logger logger;

	public WebServerConfiguration() {
		logger = Logger.getLogger(getClass().getName());
	}

}
