package com.mwt.server.ui;

import java.util.logging.Logger;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configurations for UIServer
 * 
 * @author v.manea
 */
@Configuration
@ComponentScan({"com.mwt.ui", "com.mwt.common"})
public class UIServerConfiguration {

	protected Logger logger;

	public UIServerConfiguration() {
		logger = Logger.getLogger(getClass().getName());
	}

}
