package com.medteamb.medteamb.model;


import com.medteamb.medteamb.utils.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class User {


    private String username;

    private List<String> roles;

    private String password;

    private Long userId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }


    public static class UserBuilder {

        private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        private User user = new User();

        public User build() {
            return user;
        }

        public UserBuilder withUsername(String username) {
            user.setUsername(username);
            return this;
        }

        public UserBuilder withPassword(String password) {
            user.setPassword(password);
            return this;
        }

        public UserBuilder withUserId(Long userId) {
            user.setUserId(userId);
            return this;
        }


        public UserBuilder withClearPassword(String password) {
            user.setPassword(encoder.encode(password));
            return this;
        }

        public UserBuilder withRoles(List<String> roles) {
            user.setRoles(roles);
            return this;
        }
    }

}
