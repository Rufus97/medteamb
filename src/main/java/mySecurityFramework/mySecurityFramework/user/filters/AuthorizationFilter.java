package mySecurityFramework.mySecurityFramework.user.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mySecurityFramework.mySecurityFramework.user.UserDaoImpl;
import mySecurityFramework.mySecurityFramework.user.UserServiceImpl;
import mySecurityFramework.mySecurityFramework.user.userModel.UserDetails;
import mySecurityFramework.mySecurityFramework.user.userModel.UserEntity;
import mySecurityFramework.mySecurityFramework.user.utils.CustomAnnotation;
import mySecurityFramework.mySecurityFramework.user.utils.JwtUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Handler;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    UserDaoImpl userDao;
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return "/login".equals(request.getRequestURI()) || "/register".equals(request.getRequestURI());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        Map<String, HandlerMapping> map = BeanFactoryUtils.beansOfTypeIncludingAncestors(
                context, HandlerMapping.class, true, false
        );
        UserDetails user = null;
        try {
          HandlerExecutionChain chain = map.get("requestMappingHandlerMapping")
                   .getHandler(request);
          HandlerMethod method = (HandlerMethod) chain.getHandler();
          Claims claim = null;

          if (request.getHeader("Authentication") != null){
              String token = request.getHeader("Authentication");
              claim = jwtUtils.extractAllClaims(token);
              user = userDao.getUserByUsername(claim.getSubject());
          } else {
              response.sendError(401, "unauthorized");
          }
            if (method.hasMethodAnnotation(CustomAnnotation.class) &&
                user.getRoles().get(0).equals("USER")){
                System.out.println("you're authenticated as: " + claim.get("role"));
                filterChain.doFilter(request, response);
            } else
            {
                System.out.println(claim.get("role") + " unauthorized");
                response.sendError(401, "unauthorized");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
