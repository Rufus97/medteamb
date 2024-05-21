package com.medteamb.medteamb.controller;

import com.medteamb.medteamb.Security.utils.PatientRoleAnnotation;
import com.medteamb.medteamb.model.UserEntity;
import com.medteamb.medteamb.service.dto.user.RegisterUserDTO;
import com.medteamb.medteamb.repository.UserEntityRepo;
import com.medteamb.medteamb.service.UserEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/api/v1")
public class Login {

    @Autowired
    UserEntityService userEntityService;

    @Autowired
    UserEntityRepo userEntityRepo;

    @PostMapping("/register")
    public UserEntity user(@RequestBody RegisterUserDTO request) throws AuthenticationException {
        return userEntityService.registerUser(request);
    }

    @GetMapping("/login")
    public String logging(@RequestParam String username, @RequestParam String password){
        return "Login successfull !!!";
    }

    @PatientRoleAnnotation
    @GetMapping("/getUser")
    public UserEntity getUser(@RequestAttribute Long id){

        return userEntityRepo.findById(id).get();
    }

}
