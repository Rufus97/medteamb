package mySecurityFramework.mySecurityFramework.user.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mySecurityFramework.mySecurityFramework.user.UserDaoImpl;
import mySecurityFramework.mySecurityFramework.user.UserServiceImpl;
import mySecurityFramework.mySecurityFramework.user.userModel.UserDetails;
import mySecurityFramework.mySecurityFramework.user.userModel.UserEntity;
import mySecurityFramework.mySecurityFramework.user.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.util.Map;

@Component
public class LoginFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwt;
    @Autowired
    UserServiceImpl userService;


    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getRequestURI().equals("/login");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Map<String, String[]> params = request.getParameterMap();
        Assert.notEmpty(params.get("username"), "Username required");
        Assert.notEmpty(params.get("password"), "Password required");
        UserDetails user = null;
        try {
            user = userService.checkUserCredentials(params.get("username")[0], params.get("password")[0]);
            String token = jwt.getJws(user.getUsername(), user.getUserId(), user.getRoles());
            response.setHeader("Authentication", token);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e.getMessage());
        }


    }
}
