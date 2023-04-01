package com.devwinter.authservice.adapter.input.message.kafka;

import com.devwinter.authservice.application.port.input.LogoutMemberUseCase;
import com.devwinter.authservice.domain.event.PasswordChangeEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
// import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
// @Component
@RequiredArgsConstructor
public class PasswordChangeEventConsumer {
    private final ObjectMapper objectMapper;
    private final LogoutMemberUseCase logoutMemberUseCase;

    // @KafkaListener(topics = "member-change-password")
    public void logout(String kafkaMessage) {
        log.info("Kafka Message ->: {}", kafkaMessage);

        try {
            PasswordChangeEvent event = objectMapper.readValue(kafkaMessage, PasswordChangeEvent.class);
            logoutMemberUseCase.logout(event.email());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
