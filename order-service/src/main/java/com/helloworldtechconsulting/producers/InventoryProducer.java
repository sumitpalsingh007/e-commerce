package com.helloworldtechconsulting.producers;

import com.helloworldtechconsulting.dto.InventoryEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;

@Component
public class InventoryProducer {

    private final KafkaTemplate<String, InventoryEvent> kafkaTemplate;
    private final String topic;

    public InventoryProducer(KafkaTemplate<String, InventoryEvent> kafkaTemplate,
                             @Value("${app.kafka.inventory-topic:inventory-topic}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public CompletableFuture<?> sendInventoryEvent(InventoryEvent event) {
        String key = event.getProductId().toString();
        return kafkaTemplate.send(topic, key, event);
    }
}
