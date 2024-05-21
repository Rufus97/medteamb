package com.medteamb.medteamb.service;

import com.medteamb.medteamb.Security.UserServiceImpl;
import com.medteamb.medteamb.model.UserEntity;
import com.medteamb.medteamb.service.dto.user.RegisterUserDTO;
import com.medteamb.medteamb.repository.UserEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class UserEntityService {
    @Autowired
    UserEntityRepo userEntityRepo;

    @Autowired
    UserServiceImpl userService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserEntity registerUser(RegisterUserDTO request) throws AuthenticationException {
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRoles("PATIENT");
        return userEntityRepo.save(user);
    }
}
