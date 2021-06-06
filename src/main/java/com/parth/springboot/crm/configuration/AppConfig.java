package com.parth.springboot.crm.configuration;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("com.parth.springboot.crm")
@PropertySource({"classpath:application.properties"})
public class AppConfig {

	@Bean
	public RestTemplate getRestTemplate() {
		
		return new RestTemplate();
		
	}
	
	@Bean
	@ConfigurationProperties("security.datasource")
	public DataSource securityDataSource() {
		return DataSourceBuilder.create().build();
	}
	
}
