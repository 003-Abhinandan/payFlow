package com.payflow.kafka;

import com.payflow.model.Payment;
import com.payflow.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final PaymentRepository repository;

    @KafkaListener(topics = "payment-topic", groupId = "payment-group")
    public void consume(Payment payment) {

        try {
            Thread.sleep(1000); // simulate processing

            payment.setStatus("SUCCESS");

        } catch (Exception e) {
            payment.setStatus("FAILED");
        }

        repository.save(payment);
    }
}