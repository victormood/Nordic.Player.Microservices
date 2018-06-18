package com.mwt.server.login;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

/**
 * The accounts Spring configuration. This will load dependencies from
 * the @ComponentScan package list. "Login" will need also the common module in
 * order to activate the authentication filter and other common functionality
 * 
 * @author v.manea
 */
@Configuration
@ComponentScan({ "com.mwt.login"})
@EntityScan("com.mwt.movies.ds.entities")
@EnableJpaRepositories("com.mwt.movies.ds")
@PropertySource("classpath:db-config.properties")
public class LoginConfiguration {

	protected Logger logger;

	public LoginConfiguration() {
		logger = Logger.getLogger(getClass().getName());
	}

	/**
	 * Creates an in-memory "rewards" database populated with test data for fast
	 * testing
	 */
	@Bean
	public DataSource dataSource() {
		logger.info("dataSource() invoked");

		// Create an in-memory H2 relational database containing some demo
		// accounts.
		DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:testdb/schema_account.sql")
				.addScript("classpath:testdb/data_account.sql").build();

		logger.info("dataSource = " + dataSource);

		// Sanity check
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> accounts = jdbcTemplate.queryForList("SELECT number FROM T_ACCOUNT");
		logger.info("System has " + accounts.size() + " accounts");

		// Populate with random balances
		Random rand = new Random();

		for (Map<String, Object> item : accounts) {
			String number = (String) item.get("number");
			BigDecimal balance = new BigDecimal(rand.nextInt(10000000) / 100.0).setScale(2, BigDecimal.ROUND_HALF_UP);
			jdbcTemplate.update("UPDATE T_ACCOUNT SET balance = ? WHERE number = ?", balance, number);
		}

		return dataSource;
	}

}
