package com.nadimnesar.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    @Value("${secret.jwt.key}")
    private String secretKey;

    @Value("${secret.jwt.expiration}")
    private Integer jwtExpirationInMs;

    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String extractToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token) {
        final Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .claims()
                .add("role", userDetails.getAuthorities())
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .and()
                .signWith(getSignInKey(), Jwts.SIG.HS256)
                .compact();
    }

    private Date extractExpiration(String token) {
        final Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}