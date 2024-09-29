package com.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.model.User;
import com.security.repository.UserRepository;
import com.security.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserRepository iUserRepository;
	
	@Autowired
	UserServiceImpl cUserServiceImpl;
	
	
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody User cUser) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		System.out.println("User authenticated...");
		User cUserInDb = iUserRepository.findByUsername(username);
		cUserInDb.setPassword(cUser.getPassword());
		cUserServiceImpl.addUser(cUserInDb);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
