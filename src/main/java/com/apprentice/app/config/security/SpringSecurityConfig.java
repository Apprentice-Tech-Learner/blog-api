package com.apprentice.app.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        http.authorizeRequests().antMatchers("/api/**").permitAll();

        httpSecurity
                .cors().disable() //cors 제거 - api서버임
                .csrf().disable() //csrf 임시 우회
                .formLogin().disable(); //form submit disabled

        return httpSecurity
                .authorizeRequests(
                        authorize -> authorize
//                                .requestMatchers("/users/**").permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/login/**")).permitAll()
                                .anyRequest().authenticated()
                )
                .build();
    }
}
