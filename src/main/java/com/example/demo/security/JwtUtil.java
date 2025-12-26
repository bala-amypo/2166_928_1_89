package com.example.demo.security;

import com.example.demo.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final String jwtSecret = "ThisIsAVeryStrongSecretKeyForTheDemoApplicationMustBe32Chars";
    private final long jwtExpirationMs = 86400000;

    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(Long userId, String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Needed for tests
    public String createToken(Long userId, String email, String role) {
        return generateToken(userId, email, role);
    }

    // Needed for tests
    public String generateToken(UserDetails userDetails, User user) {
        return generateToken(user.getId(), user.getEmail(), user.getRole());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Needed for tests
    public boolean validateToken(String token, UserDetails userDetails) {
        return validateToken(token);
    }
}