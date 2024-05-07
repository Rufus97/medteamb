package com.medteamb.medteamb.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.medteamb.medteamb.model.User;
import com.medteamb.medteamb.repository.UserRepository;

import it.pasqualecavallo.studentsmaterial.authorization_framework.exception.BadCredentialsException;
import it.pasqualecavallo.studentsmaterial.authorization_framework.service.UserDetails;
import it.pasqualecavallo.studentsmaterial.authorization_framework.service.UserService;
import it.pasqualecavallo.studentsmaterial.authorization_framework.utils.BCryptPasswordEncoder;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserServiceImpl (UserRepository userRepository, 
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	
	@Override
	public UserDetails checkUserCredentials(String username, String password) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> 
			new BadCredentialsException("Username " + username + " non trovato"));
		if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
			return UserDetails.
					builder().
					withClearPassword(password).
					withPassword(user.getPassword()).
					withUserId(user.getUserId()).
					withUsername(user.getUsername()).
					withRoles(Arrays.asList(user.getRoles())).
					build();
		} else {
			
			throw new BadCredentialsException("Username " + username + " non trovato");
		}
	} 
}
