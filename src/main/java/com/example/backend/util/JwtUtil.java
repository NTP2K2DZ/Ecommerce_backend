package com.example.backend.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-ms}")
    private long jwtExpirationMs;

    @Value("${app.jwt-refresh-expiration-ms}")
    private long jwtRefreshExpirationMs;

    // Generate signing key from the secret
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

//    private Key getSigningKey() {
//        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
//    }
//    private Key key(){
//        return Keys.hmacShaKeyFor(
//                Decoders.BASE64.decode(jwtSecret)
//        );
//    }

    // Generate a JWT token
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        System.out.println(jwtExpirationMs);
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtRefreshExpirationMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract username (subject) from the token
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Validate the token's authenticity and expiration
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token format: {}", e.getMessage());
        } catch (SecurityException e) {
            logger.error("JWT signature does not match: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT token is empty or has an invalid structure: {}", e.getMessage());
        }
        return false;
    }
}
