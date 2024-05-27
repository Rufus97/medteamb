package com.medteamb.medteamb.Security.filters;

import com.medteamb.medteamb.Security.UserDaoImpl;
import com.medteamb.medteamb.Security.utils.DoctorRoleAnnotation;
import com.medteamb.medteamb.Security.utils.JwtUtils;
import com.medteamb.medteamb.Security.utils.PatientRoleAnnotation;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.medteamb.medteamb.model.UserDetails;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    UserDaoImpl userDao;

    HandlerMapping handlerMapping;



    public AuthorizationFilter(ApplicationContext context) {
      this.handlerMapping = BeanFactoryUtils
              .beansOfTypeIncludingAncestors(context, HandlerMapping.class, true, false)
              .get("requestMappingHandlerMapping");
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return "/api/v1/login".equals(request.getRequestURI()) || "/api/v1/register".equals(request.getRequestURI())
                || "/api/v1/patients/create".equals(request.getRequestURI()) || "/api/v1/doctor/create".equals(request.getRequestURI());
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HandlerMethod method = null;
        try {
            System.out.println("sto mappando il metodo");
            HandlerExecutionChain chain = this.handlerMapping.getHandler(request);
            method = (HandlerMethod) chain.getHandler();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        UserDetails user = null;
        if (request.getHeader("Authentication") != null) {
            System.out.println("sto usando il token");

            String token = request.getHeader("Authentication");
            Claims claim = null;

            claim = jwtUtils.extractAllClaims(token);
            user = userDao.getUserByUsername(claim.getSubject());
            if (jwtUtils.isTokenValid(token, user)){
                System.out.println("sto validando il token");
                if (method.hasMethodAnnotation(PatientRoleAnnotation.class) &&
                        Objects.requireNonNull(user).getRoles().getFirst().equals("PATIENT")) {
                    System.out.println("you're authenticated as: " + claim.get("role"));
                    filterChain.doFilter(request, response);
                } else if (method.hasMethodAnnotation(DoctorRoleAnnotation.class) &&
                        Objects.requireNonNull(user).getRoles().getFirst().equals("DOCTOR")) {
                    System.out.println("you're authenticated as: " + claim.get("role"));
                    filterChain.doFilter(request, response);
                }
            } else {
                response.sendError(401, "invalid token");
            }

        }
        else {
        response.sendError(401, "unauthorized");
        }



    }
}
