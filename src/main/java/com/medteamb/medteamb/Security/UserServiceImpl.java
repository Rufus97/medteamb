package com.medteamb.medteamb.Security;


import com.medteamb.medteamb.Security.baseInterfaces.UserService;
import com.medteamb.medteamb.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
   UserDaoImpl userDao;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails checkUserCredentials(String username, String password) throws AuthenticationException {
        UserDetails user = userDao.getUserByUsername(username);
        if (encoder.matches(password, user.getPassword()) && user.getUsername().equals(username)){
            return user;
        } else {
            throw new AuthenticationException("wrong username or password");
        }
    }
}
