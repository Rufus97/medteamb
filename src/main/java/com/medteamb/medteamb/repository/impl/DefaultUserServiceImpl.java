package com.medteamb.medteamb.repository.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.medteamb.medteamb.model.User;
import com.medteamb.medteamb.service.UserService;
import com.medteamb.medteamb.utils.BCryptPasswordEncoder;

@Service
public class DefaultUserServiceImpl implements UserService, InitializingBean {

    Map<String, User> users = new HashMap<>();

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
    @Override
    public void afterPropertiesSet() throws Exception {
        users.put("admin", User.builder().withClearPassword("password").withUsername("admin")
                .withRoles(Arrays.asList("ROLE_ADMIN")).withUserId(1l).build());

        users.put("secretary", User.builder().withClearPassword("password").withUsername("secretary")
                .withRoles(Arrays.asList("ROLE_SECRETARY")).withUserId(2l).build());

        users.put("doctor", User.builder().withClearPassword("password").withUsername("doctor")
                .withRoles(Arrays.asList("ROLE_DOCTOR")).withUserId(3l).build());

        users.put("patient", User.builder().withClearPassword("password").withUsername("patient")
                .withRoles(Arrays.asList("ROLE_PATIENT")).withUserId(4l).build());
    }

	@Override
	public User checkUserCredentials(String username, String password) {
		User user = users.get(username);
		if(user != null ) {
			if(encoder.matches(password, user.getPassword())) {
				return user;
			} else {
				throw new RuntimeException("Invalid password for user " + username);
			}
		} else {
			throw new RuntimeException("Username " + username + " not found");
		}
		
	}


}
