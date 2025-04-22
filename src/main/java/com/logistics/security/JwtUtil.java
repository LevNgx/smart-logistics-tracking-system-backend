package com.logistics.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final long EXPIRATION_TIME = 86400000; // 1 day in ms
    private final String SECRET = "your-super-secret-jwt-key-for-signing-token"; // 🔐 Should be 256-bit key

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // 👤 Identifies the user
                .setIssuedAt(new Date()) // 🕐 When token was created
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // ⏳ Expiry
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // 🔏 Signature
                .compact(); // 🧱 Build the token
    }

    public String extractUsername(String token) {
        return parseToken(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            parseToken(token);
            System.out.println("printing here" + token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
    }
}
