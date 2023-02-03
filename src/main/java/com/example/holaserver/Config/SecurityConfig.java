package com.example.holaserver.Config;

import com.example.holaserver.Auth.JwtAuthenticationFilter;
import com.example.holaserver.Auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http
               .csrf().disable()
               .httpBasic().disable()
               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
               .and()
               .authorizeRequests()
               .anyRequest().permitAll()
               .and()
               .cors()
               .and()
               .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
