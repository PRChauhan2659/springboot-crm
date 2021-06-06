package com.parth.springboot.crm.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("securityDataSource")
	private DataSource securityDataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(securityDataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
				.antMatchers("/").hasRole("EMPLOYEE")
				.antMatchers("/customers/list").hasRole("EMPLOYEE")
				.antMatchers("/customers/showFormAdd").hasRole("MANAGER")
				.antMatchers("/customers/save").hasRole("MANAGER")
				.antMatchers("/customers/showFormUpdate").hasRole("MANAGER")
				.antMatchers("/customers/delete").hasRole("MANAGER")
			.and()
			.formLogin()
				.loginPage("/customers/login")
				.loginProcessingUrl("/authenticateUser")
				.permitAll()
			.and()
				.logout().permitAll()
			.and()
				.exceptionHandling().accessDeniedPage("/customers/access-denied");
		
//		http.authorizeRequests()
//		.antMatchers("/showFormAdd").hasRole("MANAGER")
//		.antMatchers("/save").hasRole("MANAGER")
//		.antMatchers("/showFormUpdate").hasRole("MANAGER")
//		.antMatchers("/delete").hasRole("MANAGER")
//		.antMatchers("/resources/**").permitAll()
//		.and()
//		.formLogin()
//			.permitAll()
//		.and()
//		.logout().permitAll()
//		.and()
//		.exceptionHandling().accessDeniedPage("/access-denied");
		
	}

	
	
}
