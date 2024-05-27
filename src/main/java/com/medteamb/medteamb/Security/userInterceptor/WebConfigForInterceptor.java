package com.medteamb.medteamb.Security.userInterceptor;

import com.medteamb.medteamb.Security.userInterceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfigForInterceptor implements WebMvcConfigurer {


    @Autowired
    UserInterceptor userInterceptor;

    @Autowired
    DocUserInterceptor docUserInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(userInterceptor);
        registry.addInterceptor(docUserInterceptor);
    }
}
