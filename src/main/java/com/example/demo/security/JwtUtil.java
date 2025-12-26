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

    private static final String SECRET =
            "THIS_IS_A_DEMO_SECRET_KEY_FOR_JWT_TESTS_1234567890";

    private static final long EXPIRATION = 86400000; // 24 hours

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(
            UserDetails userDetails,
            User user
    ) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("userId", user.getId())
                .claim("email", user.getEmail())
                // ⚠️ role is STRING, not enum
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + EXPIRATION)
                )
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(
            String token,
            UserDetails userDetails
    ) {
        try {
            String username = getEmailFromToken(token);
            return username.equals(userDetails.getUsername())
                    && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        return getAllClaims(token).getSubject();
    }

    private boolean isTokenExpired(String token) {
        return getAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
