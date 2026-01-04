package com.rebellion.travelblogplatform.config.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import com.rebellion.travelblogplatform.entity.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
    private static final String SECRET = "my_custom_secret_key_for_jwt";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    private static final long EXPIRATION_TIME = 60 * 60 * 1000; // 36,00,000 msec = 1 hr

    private JwtUtil(){
        // Skipped for utility class
    }

    // TOKEN GENERATION
    public static String generateToken(String email, Role role) {
        return Jwts.builder()
                    .subject(email)
                    .claim("role", role)
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SECRET_KEY)
                    .compact();
    }

    // TOKEN EXTRACTION
    public static Claims extractClaims(String token){
        return Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
    }

    public static String extractEmail(String token){
        return extractClaims(token).getSubject();
    }

    public static Role extractRole(String token){
        return extractClaims(token).get("role", Role.class);
    }

    // TOKEN VERIFICATION
    public static boolean isTokenValid(String token){
        try {
            extractClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
