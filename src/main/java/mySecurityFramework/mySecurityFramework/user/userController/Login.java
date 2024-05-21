package mySecurityFramework.mySecurityFramework.user.userController;

import jakarta.servlet.ServletContext;
import mySecurityFramework.mySecurityFramework.user.userModel.LoginUserDTO;
import mySecurityFramework.mySecurityFramework.user.userModel.RegisterUserDTO;
import mySecurityFramework.mySecurityFramework.user.userModel.UserDetails;
import mySecurityFramework.mySecurityFramework.user.userModel.UserEntity;
import mySecurityFramework.mySecurityFramework.user.userRepository.UserEntityRepo;
import mySecurityFramework.mySecurityFramework.user.userService.UserEntityService;
import mySecurityFramework.mySecurityFramework.user.utils.AnotherAnnotation;
import mySecurityFramework.mySecurityFramework.user.utils.CustomAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.util.ServletContextPropertyUtils;

import javax.naming.AuthenticationException;

@RestController
public class Login {

    @Autowired
    UserEntityService userEntityService;

    @Autowired
    UserEntityRepo userEntityRepo;

    @PostMapping("/register")
    public UserEntity user(@RequestBody RegisterUserDTO request) throws AuthenticationException {
        return userEntityService.registerUser(request);
    }

    @GetMapping("/login")
    public String logging(@RequestParam String username, @RequestParam String password){
        return "Login successfull !!!";
    }

    @CustomAnnotation
    @GetMapping("/test")
    public String aaaaaaaaaaa(){
        return "Login successfull !!!";
    }

    @GetMapping("/test1")
    public String ssssss(){
        return "Login successfull !!!";
    }
    @CustomAnnotation
    @GetMapping("/getUser")
    public UserEntity getUser(@RequestAttribute Long id){

        return userEntityRepo.findById(id).get();
    }

    @AnotherAnnotation
    @GetMapping("/getUser2")
    public UserEntity getUserbad(@RequestAttribute Long id){

        return userEntityRepo.findById(id).get();
    }
}
