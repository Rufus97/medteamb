package mySecurityFramework.mySecurityFramework.user.baseInterfaces;

import mySecurityFramework.mySecurityFramework.user.userModel.UserDetails;

import javax.naming.AuthenticationException;

public interface UserService {

    public UserDetails checkUserCredentials(String username, String password) throws AuthenticationException;
}
