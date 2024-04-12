package com.medteamb.medteamb.filter;

import java.io.IOException;
import java.util.List;

import com.medteamb.medteamb.security.PublicEndpoint;
import com.medteamb.medteamb.utils.Constants;
import com.medteamb.medteamb.utils.JwtUtils;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(2)
public class AuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return Boolean.TRUE.equals(((Boolean)request.getAttribute(Constants.SKIP_AUTHORIZATION_FILTERCHAIN_ATTRIBUTE)));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            HandlerMethod handlerMethod = (HandlerMethod) request
                    .getAttribute(Constants.HANDLE_METHOD_FOR_AUTHORIZATION_ATTRIBUTE);
            String authenticationHeader = request.getHeader(Constants.X_AUTHENTICATION_HEADER);

            if (authenticationHeader == null && handlerMethod.hasMethodAnnotation(PublicEndpoint.class)) {
                filterChain.doFilter(request, response);
            } else if (authenticationHeader != null) {
                String[] tokens = authenticationHeader.split(" ");
                if (tokens[0].equals("Bearer")) {
                    Jws<Claims> claims = jwtUtils.decodeJwt(tokens[1]);
                    if (claims != null) {
                        AuthenticationContext.Principal principal = new AuthenticationContext.Principal(
                                claims.getBody().getSubject(),
                                claims.getBody().get(Constants.CLAIM_USER_ID) != null ? ((Integer)claims.getBody().get(Constants.CLAIM_USER_ID)).longValue() : null,
                                (List<String>) claims.getBody().get(Constants.CLAIM_USER_ROLES, List.class));
                        AuthenticationContext.set(principal);
                        filterChain.doFilter(request, response);
                    } else {
                        response.sendError(HttpStatus.FORBIDDEN.value());
                    }
                } else {
                    response.sendError(HttpStatus.FORBIDDEN.value());
                }
            } else {
                response.sendError(HttpStatus.FORBIDDEN.value());
            }
        } finally {
            AuthenticationContext.remove();
        }

    }

}
