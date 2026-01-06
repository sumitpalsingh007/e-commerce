package com.helloworldtechconsulting.paymentservice.listener;

import com.helloworldtechconsulting.paymentservice.model.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    private static final Logger log = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "order.created", groupId = "payment-group")
    public void consumeOrderEvent(OrderEvent event) {
        log.info("Payment service received order event: orderId={}, productId={}, quantity={}",
                event.getOrderId(), event.getProductId(), event.getQuantity());
    }
}
