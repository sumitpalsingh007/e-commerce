package com.helloworldtechconsulting.config;

import com.helloworldtechconsulting.service.ProductEstimator;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIServiceConfiguration {

    @Bean
    ProductEstimator productEstimator(ChatLanguageModel chatLanguageModel, RetrievalAugmentor retrievalAugmentor){
        return AiServices.builder(ProductEstimator.class)
                .chatLanguageModel(chatLanguageModel)
                .retrievalAugmentor(retrievalAugmentor)
                .build();
    }
}
