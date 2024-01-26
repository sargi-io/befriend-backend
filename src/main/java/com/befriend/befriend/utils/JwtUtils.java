package com.befriend.befriend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import java.util.Date;

public class JwtUtils {

    private static final long EXPIRATION_TIME = 900_000; // 15 minutes
    private static final String SECRET = "YourSecretKey"; // Use a strong, unique key

    public static String generateJwtToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        
        return JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
    }
}
