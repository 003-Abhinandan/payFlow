package com.payflow.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderId;

    private Double amount;

    @Column(unique = true)
    private String idempotencyKey;

    private String status; // PENDING, SUCCESS, FAILED

    private LocalDateTime createdAt;
}