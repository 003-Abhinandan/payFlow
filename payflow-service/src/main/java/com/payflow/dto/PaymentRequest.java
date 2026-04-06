package com.payflow.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private String orderId;
    private Double amount;
    private String idempotencyKey;
}