package com.example.authenticationservice.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.Map;

@Component
@Slf4j
public class JwtHelper {

    @Value("${jwt.secret}")
    private String SECRET;

//    private final static String SECRET = "top-secret";

    private final static long EXPIRATION = 5 * 60 * 60 * 60;

    public String generateToken(String email) {
        System.out.println("JwtHelper - generateToken");
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public String generateRefreshToken(String email) {
        System.out.println("JwtHelper - generateRefreshToken");
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION * 60))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public String getSubject(String token) {
        System.out.println("JwtHelper - getSubject");
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        System.out.println("JwtHelper - validateToken");
        System.out.println(token);
        try {
            Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException |
                 UnsupportedJwtException | IllegalArgumentException e) {
            log.error("Error to parse token: ", e);
        }

        return false;
    }

    public String doGenerateRefreshToken(Map<String, Object> claims, String subject) {
        System.out.println("JwtHelper - doGenerateRefreshToken");
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }


    public String getUsernameFromToken(String token) {
        System.out.println("JwtHelper - getUsernameFromToken");
        String result = null;
        try {
            result = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            System.out.println(e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
