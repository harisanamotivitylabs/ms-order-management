package com.notificationservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
@EnableKafka
public class NotificationServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	@KafkaListener(topics = "notificationTopic")
	public void handleNotification(OrderPlacedEvent orderPlacedEvent)
	{
		// TODO email notification

		System.out.println("sfdghnm,.");
		log.info("Received Notification for Order- {}",orderPlacedEvent.getOrderNumber());
	}
}
