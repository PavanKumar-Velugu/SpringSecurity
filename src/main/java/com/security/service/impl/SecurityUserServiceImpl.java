package com.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.security.model.User;
import com.security.repository.UserRepository;

@Component
public class SecurityUserServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository iUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User cUser = iUserRepository.findByUsername(username);
		
		if(cUser != null) {
			
			return org.springframework.security.core.userdetails.User.builder()
				.username(cUser.getUsername())
				.password(cUser.getPassword())
				.roles(cUser.getRoles())
				.build();			
		}
		
		throw new UsernameNotFoundException("User not found with username: "+username);
	}

}
 