package mySecurityFramework.mySecurityFramework.user.userService;

import mySecurityFramework.mySecurityFramework.user.UserServiceImpl;
import mySecurityFramework.mySecurityFramework.user.userModel.LoginUserDTO;
import mySecurityFramework.mySecurityFramework.user.userModel.RegisterUserDTO;
import mySecurityFramework.mySecurityFramework.user.userModel.UserDetails;
import mySecurityFramework.mySecurityFramework.user.userModel.UserEntity;
import mySecurityFramework.mySecurityFramework.user.userRepository.UserEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class UserEntityService {
    @Autowired
    UserEntityRepo userEntityRepo;

    @Autowired
    UserServiceImpl userService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserEntity registerUser(RegisterUserDTO request) throws AuthenticationException {
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole("USER");

        return userEntityRepo.save(user);

    }
}
