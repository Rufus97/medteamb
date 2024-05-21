package com.medteamb.medteamb.Security.filters;

import com.medteamb.medteamb.Security.UserDaoImpl;
import com.medteamb.medteamb.Security.utils.JwtUtils;
import com.medteamb.medteamb.Security.utils.PatientRoleAnnotation;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.medteamb.medteamb.model.UserDetails;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import java.io.IOException;
import java.util.Map;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    UserDaoImpl userDao;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return "/api/v1/login".equals(request.getRequestURI()) || "/api/v1/register".equals(request.getRequestURI());
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

            if (!request.getHeader("Authentication").isBlank()){
                String token = request.getHeader("Authentication");
                claim = jwtUtils.extractAllClaims(token);
                user = userDao.getUserByUsername(claim.getSubject());
            } else {
                response.sendError(401, "unauthorized");
            }
            if (method.hasMethodAnnotation(PatientRoleAnnotation.class) &&
                    user.getRoles().get(0).equals("PATIENT")){
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
