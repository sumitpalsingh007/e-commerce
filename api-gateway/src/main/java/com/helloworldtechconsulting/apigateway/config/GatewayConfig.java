package com.helloworldtechconsulting.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth_route", r -> r.path("/auth/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://auth-service:8080"))
                .route("other_route", r -> r.path("/other/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://other-service:8080"))
                // Add more routes for other microservices
                .build();
    }
}
