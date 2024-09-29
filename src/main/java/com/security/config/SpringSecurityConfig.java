package com.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.security.service.impl.SecurityUserServiceImpl;


@Configuration
@EnableWebSecurity
@SuppressWarnings("deprecation")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	SecurityUserServiceImpl cSecurityUserServiceImpl;

	protected void configure(HttpSecurity cHttpSecurity) throws Exception {
		
		cHttpSecurity.authorizeRequests()
			.antMatchers("/user").authenticated()
			.anyRequest().permitAll()
			.and()
			.httpBasic();
		
		cHttpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().csrf().disable();	
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {	
		System.out.println("In Spring configure");
		auth.userDetailsService(cSecurityUserServiceImpl).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
