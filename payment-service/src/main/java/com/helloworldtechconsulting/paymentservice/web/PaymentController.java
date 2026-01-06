package com.helloworldtechconsulting.paymentservice.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @PostMapping("/{orderId}")
    public String processPayment(@PathVariable Long orderId) {
        return "Payment processed for order: " + orderId;
    }
}
