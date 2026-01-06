package com.helloworldtechconsulting.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentClient {

    private static final String PAYMENT_SERVICE_URL = "http://PAYMENT-SERVICE/payments";

    private final RestTemplate restTemplate;

    public PaymentClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String processPayment(Long orderId) {
        return restTemplate.postForObject(PAYMENT_SERVICE_URL + "/" + orderId, null, String.class);
    }
}
