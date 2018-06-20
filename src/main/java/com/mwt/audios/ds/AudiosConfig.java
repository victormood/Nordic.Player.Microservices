package com.mwt.audios.ds;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({ "classpath:db-audio-config.properties" })
@EnableJpaRepositories(
		basePackages = "com.mwt.audios.ds", 
		entityManagerFactoryRef = "audioEntityManager", 
		transactionManagerRef = "audioTransactionManager")
public class AudiosConfig {
	
	@Autowired
	private Environment env;

	@Bean
	public LocalContainerEntityManagerFactoryBean audioEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(audioDataSource());
		em.setPackagesToScan(new String[] { "com.mwt.audios.ds.entities" });

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Bean
	public DataSource audioDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.audio.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.audio.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.audio.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.audio.datasource.password"));

		return dataSource;
	}

	@Bean
	public PlatformTransactionManager audioTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(audioEntityManager().getObject());
		return transactionManager;
	}
}