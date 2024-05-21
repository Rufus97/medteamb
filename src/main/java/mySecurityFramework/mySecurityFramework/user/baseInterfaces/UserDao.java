package mySecurityFramework.mySecurityFramework.user.baseInterfaces;

import mySecurityFramework.mySecurityFramework.user.userModel.UserDetails;

public interface UserDao {

    public UserDetails getUserByUsername(String username);
}
