package com.medteamb.medteamb.Security.baseInterfaces;

import com.medteamb.medteamb.model.UserDetails;

import javax.naming.AuthenticationException;

public interface UserService {

    public UserDetails checkUserCredentials(String username, String password) throws AuthenticationException;
}
