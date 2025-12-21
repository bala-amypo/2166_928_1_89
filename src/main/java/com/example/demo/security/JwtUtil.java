package com.example.demo.security;

import com.example.demo.model.User;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    public String generateToken(String email, User user) {
        // Simple token placeholder (tests usually mock this)
        return "jwt-token-for-" + email;
    }

    public boolean validateToken(String token, String email) {
        return token != null && token.contains(email);
    }
}
