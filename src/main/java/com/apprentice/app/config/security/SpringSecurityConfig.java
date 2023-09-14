package com.apprentice.app.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        http.authorizeRequests().antMatchers("/api/**").permitAll();

        httpSecurity
                .csrf().disable() //csrf 임시 우회
                .formLogin().disable(); //form submit disabled

        return httpSecurity
                .authorizeRequests(
                        authorize -> authorize
//                                .requestMatchers("/users/**").permitAll()
//                                .requestMatchers("/login").permitAll()
                                .anyRequest().authenticated()
                )
                .build();
    }
}
