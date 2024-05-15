package com.medteamb.medteamb.controller;


import com.medteamb.medteamb.model.UserEntity;
import com.medteamb.medteamb.service.UserEntityService;
import com.medteamb.medteamb.service.dto.user.UserLoginDTO;
import com.medteamb.medteamb.service.dto.user.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account")
public class LoginController {

    @Autowired
    UserEntityService userEntityService;

@PostMapping("/login")
public UserEntity login(UserLoginDTO loginDTO){
    return userEntityService.login(loginDTO);
}

}
