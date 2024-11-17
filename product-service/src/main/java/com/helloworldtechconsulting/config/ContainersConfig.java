package com.helloworldtechconsulting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContainersConfig {

    @Bean
    @ServiceConnection
    @RestartScope
    PostgreSQLContainer<?> postgreSQLContainer() {
        return new PostgresSQLContainer<>("postgres:16-alpine");
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
