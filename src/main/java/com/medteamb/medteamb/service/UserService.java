package com.medteamb.medteamb.service;

import com.medteamb.medteamb.model.User;

public interface UserService {

    User checkUserCredentials(String username, String password);
}
