package mySecurityFramework.mySecurityFramework.user.userInterceptor;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mySecurityFramework.mySecurityFramework.user.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authentication");
        if (token != null){
            String authToken = request.getHeader("Authentication");
            Claims claim = jwtUtils.extractAllClaims(authToken);
            request.setAttribute("id", claim.get("id"));
            return true;
        }
        return true;
    }
}
