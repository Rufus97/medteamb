package com.medteamb.medteamb.repository.impl;

import com.medteamb.medteamb.model.User;
import com.medteamb.medteamb.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class DefaultUserRepositoryImpl implements UserService, InitializingBean {

    Map<String, User> users = new HashMap<>();

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


}
