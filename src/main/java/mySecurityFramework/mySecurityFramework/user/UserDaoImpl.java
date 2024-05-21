package mySecurityFramework.mySecurityFramework.user;

import mySecurityFramework.mySecurityFramework.user.baseInterfaces.UserDao;
import mySecurityFramework.mySecurityFramework.user.userModel.UserDetails;
import mySecurityFramework.mySecurityFramework.user.userModel.UserEntity;
import mySecurityFramework.mySecurityFramework.user.userRepository.UserEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    UserEntityRepo repo;

    @Override
    public UserDetails getUserByUsername(String username) {
        UserEntity myUser = repo.findByUsername(username).orElseThrow(
                ()-> new RuntimeException("User not found")
        );
        List<String> roles = Arrays.asList(myUser.getRole());
        return UserDetails.userDetailsBuilder().withUserId(myUser.getId()).withUsername(myUser.getUsername())
                .withPassword(myUser.getPassword()).withRoles(roles).build();
    }
}
