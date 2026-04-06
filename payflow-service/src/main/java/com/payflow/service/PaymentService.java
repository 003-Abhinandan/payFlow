package com.payflow.service;

import com.payflow.dto.PaymentRequest;
import com.payflow.kafka.KafkaProducerService;
import com.payflow.model.Payment;
import com.payflow.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;
    private final KafkaProducerService kafkaProducer;

    public String processPayment(PaymentRequest request) {

        Optional<Payment> existing =
                repository.findByIdempotencyKey(request.getIdempotencyKey());

        if (existing.isPresent()) {
            return "Duplicate request. Status: " + existing.get().getStatus();
        }

        Payment payment = new Payment();
        payment.setOrderId(request.getOrderId());
        payment.setAmount(request.getAmount());
        payment.setIdempotencyKey(request.getIdempotencyKey());
        payment.setStatus("PENDING");
        payment.setCreatedAt(LocalDateTime.now());

        repository.save(payment);

        kafkaProducer.sendPaymentEvent(payment);

        return "Payment initiated";
    }
}