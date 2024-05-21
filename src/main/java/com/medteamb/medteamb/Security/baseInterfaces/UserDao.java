package com.medteamb.medteamb.Security.baseInterfaces;

import com.medteamb.medteamb.model.UserDetails;

public interface UserDao {

    public UserDetails getUserByUsername(String username);
}
