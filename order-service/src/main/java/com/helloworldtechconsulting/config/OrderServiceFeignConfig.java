package com.helloworldtechconsulting.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class OrderServiceFeignConfig {

    @Bean
    public RequestInterceptor forwardAuthTokenInterceptor() {
        return requestTemplate -> {
            try {
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (attributes != null) {
                    // Get the 'Authorization' header from the current request
                    String authToken = attributes.getRequest().getHeader("Authorization");
                    if (authToken != null && !authToken.isEmpty()) {
                        // Add it to the Feign request
                        requestTemplate.header("Authorization", authToken);
                    }
                }
            } catch (Exception e) {
                // Log the exception, e.g., if not in a web context
                System.err.println("Could not get auth token from request context: " + e.getMessage());
            }
        };
    }
}
