package com.helloworldtechconsulting.config;

import com.helloworldtechconsulting.filter.JwtAuthenticationFilter;
import com.helloworldtechconsulting.model.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static com.helloworldtechconsulting.model.Permission.ADMIN_CREATE;
import static com.helloworldtechconsulting.model.Permission.ADMIN_DELETE;
import static com.helloworldtechconsulting.model.Permission.ADMIN_READ;
import static com.helloworldtechconsulting.model.Permission.ADMIN_UPDATE;
import static com.helloworldtechconsulting.model.Permission.MANAGER_CREATE;
import static com.helloworldtechconsulting.model.Permission.MANAGER_DELETE;
import static com.helloworldtechconsulting.model.Permission.MANAGER_READ;
import static com.helloworldtechconsulting.model.Permission.MANAGER_UPDATE;
import static com.helloworldtechconsulting.model.Role.ADMIN;
import static com.helloworldtechconsulting.model.Role.MANAGER;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private static final String[] WHITE_LIST_URL = {"/api/v1/auth/**",
                                                    "/v2/api-docs",
                                                    "/v3/api-docs",
                                                    "/v3/api-docs/**",
                                                    "/swagger-resources",
                                                    "/swagger-resources/**",
                                                    "/configuration/ui",
                                                    "/configuration/security",
                                                    "/swagger-ui/**",
                                                    "/webjars/**",
                                                    "/swagger-ui.html"};

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(req ->
                   req.requestMatchers(WHITE_LIST_URL).permitAll()
                  .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                  .requestMatchers(GET, "/api/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
                  .requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
                  .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
                  .requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())
                  .anyRequest().authenticated())
            .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .logout(logout ->
                        logout.logoutUrl("/api/v1/auth/logout")
                              .addLogoutHandler(logoutHandler)
                              .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()));
        return http.build();
    }

    //@Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("http://localhost:3000"); // Allow all origins, you can adjust as needed
        corsConfig.addAllowedMethod("POST"); // Allow all HTTP methods, you can adjust as needed
        corsConfig.addAllowedMethod("GET"); // Allow all HTTP methods, you can adjust as needed
        corsConfig.addAllowedHeader("*"); // Allow all headers, you can adjust as needed
        corsConfig.addAllowedHeader("Authorization");
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsFilter(source);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);  // if you are using cookies or authentication headers
            }
        };
    }
}
