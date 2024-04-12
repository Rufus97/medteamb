package com.medteamb.medteamb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.medteamb.medteamb.security.ExclusionPatterEvaluator;

@Configuration
@Import(DefinitelyBasicAuthorizationFrameworkAutoconfiguration.class)
public class AuthorizationConfiguration {
    @Bean
    public ExclusionPatterEvaluator exclusionPatterEvaluator() {
        return new ExclusionPatterEvaluator().mustExcludeAntPathMatchers("/litterally-unfiltered", "/litterally-unfiltered/**");
    }

}