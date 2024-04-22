package com.mrbonk97.springnextjsoauth2jwtserver.config;

import com.mrbonk97.springnextjsoauth2jwtserver.oauth2.CustomOAuth2SuccessHandler;
import com.mrbonk97.springnextjsoauth2jwtserver.oauth2.CustomOAuth2UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private CustomOAuth2UserService oauth2UserService;
    private CustomOAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable);
        http.formLogin(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.oauth2Login(o ->
                o
                        .authorizationEndpoint(point -> point.baseUri("/oauth2/authorization"))
                        .userInfoEndpoint(point -> point.userService(oauth2UserService))
                        .redirectionEndpoint(point -> point.baseUri("/oauth2/callback/*"))
                        .successHandler(oAuth2SuccessHandler)
        );




        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }
}

