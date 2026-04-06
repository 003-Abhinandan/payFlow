package com.payflow.kafka;

import com.payflow.model.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Payment> kafkaTemplate;

    private static final String TOPIC = "payment-topic";

    public void sendPaymentEvent(Payment payment) {
        kafkaTemplate.send(TOPIC, payment);
    }
}