package com.medteamb.medteamb.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class UserDetails {
    private String username;

    private List<String> roles;

    private String password;

    private Long userId;

    public static UserDetailsBuilder userDetailsBuilder(){
        return new UserDetailsBuilder();
    }


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



    public static class UserDetailsBuilder {

        private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        private UserDetails userDetails = new UserDetails();

        public UserDetails build() {
            return userDetails;
        }

        public UserDetailsBuilder withUsername(String username) {
            userDetails.setUsername(username);
            return this;
        }

        public UserDetailsBuilder withPassword(String password) {
            userDetails.setPassword(password);
            return this;
        }

        public UserDetailsBuilder withUserId(Long userId) {
            userDetails.setUserId(userId);
            return this;
        }


        public UserDetailsBuilder withClearPassword(String password) {
            userDetails.setPassword(encoder.encode(password));
            return this;
        }

        public UserDetailsBuilder withRoles(List<String> roles) {
            userDetails.setRoles(roles);
            return this;
        }
    }
}
