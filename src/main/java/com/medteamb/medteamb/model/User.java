package com.medteamb.medteamb.model;


// import com.medteamb.medteamb.utils.BCryptPasswordEncoder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Table (name = "user")
public class User {


    private String username;

    private String roles;

    private String password;

    @Id
    private Long userId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
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

/*        private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

 */

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


/*        public UserBuilder withClearPassword(String password) {
            user.setPassword(encoder.encode(password));
            return this;
        }

 */

        public UserBuilder withRoles(String roles) {
            user.setRoles(roles);
            return this;
        }
    }

}
