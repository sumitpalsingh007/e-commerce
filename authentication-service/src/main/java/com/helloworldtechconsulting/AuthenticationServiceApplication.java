package com.helloworldtechconsulting;

import com.helloworldtechconsulting.model.RegisterRequest;
import com.helloworldtechconsulting.model.Role;
import com.helloworldtechconsulting.service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication/*(scanBasePackages = {"com.helloworldtechconsulting.config"})*/
//@EnableWebMvc
//@ComponentScan(basePackages = {"com.helloworldtechconsulting.config"})
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuthenticationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AuthenticationService service) {
		return args -> {
			var admin = RegisterRequest.builder()
									   .firstname("Admin")
									   .lastname("Admin")
									   .email("admin@mail.com")
									   .password("password")
									   .role(Role.ADMIN)
									   .build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var manager = RegisterRequest.builder()
										 .firstname("Admin")
										 .lastname("Admin")
										 .email("manager@mail.com")
										 .password("password")
										 .role(Role.MANAGER)
										 .build();
			System.out.println("Manager token: " + service.register(manager).getAccessToken());

		};
	}

}