package com.helloworldtechconsulting.authenticationservice.filter;

import com.helloworldtechconsulting.authenticationservice.util.JwtTokenUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Retrieve the JWT token from the request header
        String token = extractToken(request);

        // Validate and authenticate the token
        if (token != null && JwtTokenUtil.validateToken(token)) {
            Authentication authentication = JwtTokenUtil.getAuthentication(token);

            // Set the authentication in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String token = null;
        // Get the Authorization header from the request
        String header = request.getHeader("Authorization");
        // Check if the header is present and starts with "Bearer "
        if (StringUtils.isNotBlank(header) && header.startsWith("Bearer ")) {
            // Extract the token from the header
            token = header.substring(7).trim();
        }
        return token;
    }
}
