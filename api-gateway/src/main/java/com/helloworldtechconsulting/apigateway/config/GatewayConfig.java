package com.helloworldtechconsulting.apigateway.config;

import com.helloworldtechconsulting.apigateway.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth_route", r -> r.path("/auth/**")
                        .filters(f -> f.rewritePath("/auth/(?<segment>.*)", "/api/v1/auth/${segment}"))
                        .uri("http://localhost:8081"))
                // ... existing code ...
                .route("product_route", r -> r.path("/product/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter)
                                .rewritePath("/product/(?<segment>.*)", "/api/v1/product/${segment}"))
                        .uri("http://localhost:8083"))
                // ... existing code ...
                .route("order_route", r -> r.path("/order/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter)
                                .rewritePath("/order/(?<segment>.*)", "/api/v1/orders/${segment}"))
                        .uri("http://localhost:8082"))
                // ... existing code ...
                .build();
    }
}
