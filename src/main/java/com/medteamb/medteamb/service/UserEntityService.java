package com.medteamb.medteamb.service;

import com.medteamb.medteamb.model.UserEntity;
import com.medteamb.medteamb.repository.UserEntityRepository;
import com.medteamb.medteamb.service.dto.user.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEntityService {

    @Autowired
    private UserEntityRepository userEntityRepository;


    public UserEntity login(UserLoginDTO loginDTO) {
        UserEntity user = new UserEntity();
        user.setUsername(loginDTO.getUsername());
        user.setPassword(loginDTO.getPassword());
        user.setRoles("PATIENT");
       return userEntityRepository.save(user);

    }
}
