package com.security.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.model.User;
import com.security.repository.UserRepository;
import com.security.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/public")
public class PublicController {
	
	@Autowired
	UserRepository iUserRepository;
	
	@Autowired
	UserServiceImpl cUserServiceImpl;
	
	
	@PostMapping("/add-user")
	public ResponseEntity<User> addUser(@RequestBody User cUser) {
		
		try {
			if(cUser != null) {	
				User newUser = new User();
				newUser.setUsername(cUser.getUsername());
				newUser.setPassword(cUser.getPassword());	
				newUser.setRoles(cUser.getRoles());
				newUser.setStatusId(cUser.getStatusId());
				newUser.setCreationDate(cUser.getCreationDate());
				newUser.setLastupdateDate(cUser.getLastupdateDate());
				newUser = cUserServiceImpl.addUser(newUser);
				
				return new ResponseEntity<User>(newUser,HttpStatus.CREATED);
			}
		} catch (Exception e) {
			System.out.println("Exception Occured while adding user.");
			e.printStackTrace();
		}
		return null;		
	}

	@GetMapping("/getuser/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id) {
		
		Optional<User> userDetails = iUserRepository.findById(id);
		if(userDetails.isPresent()) {
			return new ResponseEntity<User>(userDetails.get() ,HttpStatus.OK);
		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/getuser/{username}")
	public ResponseEntity<User> getUserById(@PathVariable String username) {
		
		User cUser = iUserRepository.findByUsername(username);
		if(cUser != null) {
			return new ResponseEntity<User>(cUser ,HttpStatus.OK);
		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
}
