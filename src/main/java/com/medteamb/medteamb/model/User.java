package com.medteamb.medteamb.model;


import java.util.Objects;

//import com.medteamb.medteamb.utils.BCryptPasswordEncoder;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
  
    @Override
	public int hashCode() {
		return Objects.hash(password, roles, userId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(password, other.password) && Objects.equals(roles, other.roles)
				&& Objects.equals(userId, other.userId) && Objects.equals(username, other.username);
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
