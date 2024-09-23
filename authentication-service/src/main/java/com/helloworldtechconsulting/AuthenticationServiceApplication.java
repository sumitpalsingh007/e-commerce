package com.helloworldtechconsulting;

import com.helloworldtechconsulting.model.RegisterRequest;
import com.helloworldtechconsulting.model.Role;
import com.helloworldtechconsulting.service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

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
										 .firstname("Manager")
										 .lastname("Manager")
										 .email("manager@mail.com")
										 .password("password")
										 .role(Role.MANAGER)
										 .build();
			System.out.println("Manager token: " + service.register(manager).getAccessToken());

			// Uncomment the following lines to create a user for quick testing purposes.
//			var user = RegisterRequest.builder()
//					.firstname("User")
//					.lastname("User")
//					.email("user@mail.com")
//					.password("password")
//					.role(Role.USER)
//					.build();
//			System.out.println("User token: " + service.register(user).getAccessToken());

		};
	}

}