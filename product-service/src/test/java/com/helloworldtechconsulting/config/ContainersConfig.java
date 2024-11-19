package com.helloworldtechconsulting.config;

import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.ollama.OllamaContainer;
import org.testcontainers.utility.DockerImageName;

@Configuration
public class ContainersConfig {

    @Bean
    @ServiceConnection
    @RestartScope
    PostgreSQLContainer<?> postgreSQLContainer() {
        return new PostgreSQLContainer<>("postgres:16-alpine");
    }

    @Bean
    OllamaContainer ollama(DynamicPropertyRegistry properties){
        OllamaContainer ollamaContainer = new OllamaContainer(
                DockerImageName.parse("ilopezluna/llama3.2:3.12-3b")
                        .asCompatibleSubstituteFor("ollama/ollama")
        ).withReuse(true);

        properties.add("langchain4j.ollama.chat-model.base-url", ollamaContainer::getEndpoint);
        properties.add("langchain4j.ollama.chat-model.model-name", () -> "llama3.2:3b");

        return ollamaContainer;
    }
}
