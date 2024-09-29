package com.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.security.model.User;
import com.security.repository.UserRepository;

@Component
public class UserServiceImpl {
	
	@Autowired
	UserRepository iUserRepository;
	
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public User addUser(User newUser) {

		newUser.setUsername(newUser.getUsername());
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));		
		iUserRepository.save(newUser);		
		return newUser;
		
	}

}
