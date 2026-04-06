package com.payflow.controller;

import com.payflow.dto.PaymentRequest;
import com.payflow.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public String createPayment(@RequestBody PaymentRequest request) {
        return paymentService.processPayment(request);
    }
}