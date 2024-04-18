package com.medteamb.medteamb.config;

import com.medteamb.medteamb.filter.AuthorizationFilter;
import com.medteamb.medteamb.filter.DetectMethodHandlerFilter;
import com.medteamb.medteamb.filter.LoginFilter;
import com.medteamb.medteamb.filter.MethodSecurityFilter;
import com.medteamb.medteamb.service.UserService;
import com.medteamb.medteamb.utils.BCryptPasswordEncoder;
import com.medteamb.medteamb.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = { "authframework.jwt.hskey", "authframework.jwt.audience", "authframework.jwt.token-ttl" })
public class DefinitelyBasicAuthorizationFrameworkAutoconfiguration {

    private static final Logger logger = LoggerFactory.getLogger(DefinitelyBasicAuthorizationFrameworkAutoconfiguration.class);

    @Bean
    public LoginFilter loginFilter() {
        logger.info("Constructing loginFilter");
        return new LoginFilter();
    }

    @Bean
    public AuthorizationFilter authorizationFilter() {
        logger.info("Constructing authorizationFilter");
        return new AuthorizationFilter();
    }

    @Bean
    public DetectMethodHandlerFilter detectMethodHandlerFilter(ApplicationContext applicationContext) {
        logger.info("Constructing detectMethodHandlerFilter");
        return new DetectMethodHandlerFilter(applicationContext);
    }

    @Bean
    public MethodSecurityFilter methodSecurityFilter() {
        logger.info("Constructing methodSecurityFilter");
        return new MethodSecurityFilter();
    }

    @Bean
    @ConditionalOnMissingBean(value = UserService.class)
    public UserService userService() {
        logger.info("Constructing userService");
        return (username, password) -> null;
    }

    @Bean
    @ConditionalOnMissingBean(value = BCryptPasswordEncoder.class)
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        logger.info("Constructing bCryptPasswordEncoder");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtUtils jwtUtils() {
        logger.info("Constructing jwtUtils");
        return new JwtUtils();
    }
}