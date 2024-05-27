package com.medteamb.medteamb.Security;

import com.medteamb.medteamb.Security.baseInterfaces.UserDao;
import com.medteamb.medteamb.model.UserEntity;

import com.medteamb.medteamb.model.UserDetails;
import com.medteamb.medteamb.repository.UserEntityRepo;
import com.medteamb.medteamb.service.ExceptionHandler.CustomException.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    UserEntityRepo repo;

    @Override
    public UserDetails getUserByUsername(String username) {
        UserEntity myUser = repo.findByUsername(username).orElseThrow(
                ()-> new NotFound("User not found")
        );
        List<String> roles = Arrays.asList(myUser.getRoles());
        return UserDetails.userDetailsBuilder().withUserId(myUser.getId()).withUsername(myUser.getUsername())
                .withPassword(myUser.getPassword()).withRoles(roles).build();
    }
}
