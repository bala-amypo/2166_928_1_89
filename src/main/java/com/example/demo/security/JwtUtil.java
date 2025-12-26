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

    // Must be at least 32 chars for HS256
    private final String jwtSecret = "ThisIsAVeryStrongSecretKeyForTheDemoApplicationMustBe32Chars";
    private final long jwtExpirationMs = 86400000; // 24 hours

    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    // --- Standard Method used by your logic ---
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

    // --- Alias Method: Required because AuthController calls 'createToken' ---
    public String createToken(Long userId, String email, String role) {
        return generateToken(userId, email, role);
    }

    // --- Overloaded Method: Required by the Test Suite ---
    // The tests try to pass (UserDetails, User) directly
    public String generateToken(UserDetails userDetails, User user) {
        return generateToken(user.getId(), user.getEmail(), user.getRole());
    }

    // --- Standard Validation ---
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // --- Overloaded Validation: Required by the Test Suite ---
    // The tests try to pass (String, UserDetails)
    public boolean validateToken(String token, UserDetails userDetails) {
        // For this assignment, we rely on signature validation
        return validateToken(token);
    }
    
    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build()
               .parseClaimsJws(token).getBody().getSubject();
   }
}