package com.helloworldtechconsulting.authenticationservice.util;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JwtTokenUtil {

    private static final String SECRET_KEY = "sps-tech-secret-key"; // Replace with your own secret key
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    public static String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userDetails.getUsername());
        // You can add more claims as needed, such as roles, permissions, etc.

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();

        // You can retrieve additional claims from the token and create a UserDetails object
        // based on your user details implementation.

        // Create a list of authorities (roles, permissions, etc.) if available
        List<GrantedAuthority> authorities = new ArrayList<>();
        // Add the authorities to the list

        // Create and return an Authentication object
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }
}
