package com.example.demo.config;

import com.example.demo.security.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtRequestFilter jwtFilter) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**", "/auth/**", "/swagger-ui/**", "/v3/api-docs/**", "/hello-servlet").permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    // Internal JWT Filter Class
    @Configuration
    public static class JwtRequestFilter extends OncePerRequestFilter {
        private final UserDetailsService userDetailsService;
        private final JwtUtil jwtUtil;

        public JwtRequestFilter(UserDetailsService userDetailsService, JwtUtil jwtUtil) {
            this.userDetailsService = userDetailsService;
            this.jwtUtil = jwtUtil;
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
                throws ServletException, IOException {
            final String authHeader = request.getHeader("Authorization");
            String username = null;
            String jwt = null;

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                jwt = authHeader.substring(7);
                try { username = jwtUtil.extractUsername(jwt); } catch (Exception ignored) {}
            }

            if (username != null && org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication() == null) {
                var userDetails = this.userDetailsService.loadUserByUsername(username);
                if (jwtUtil.validateToken(jwt, userDetails)) {
                    var authToken = new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    org.springframework.security.core.context.SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            chain.doFilter(request, response);
        }
    }
}