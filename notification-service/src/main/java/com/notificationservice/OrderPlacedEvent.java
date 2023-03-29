package com.notificationservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EnableKafkaStreams
public class OrderPlacedEvent {
    private String orderNumber;
}
