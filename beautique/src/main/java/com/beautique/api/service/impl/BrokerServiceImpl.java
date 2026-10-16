package com.beautique.api.service.impl;

import com.beautique.api.config.RabbitMQConfig;
import com.beautique.api.service.BrokerService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@AllArgsConstructor
public class BrokerServiceImpl implements BrokerService {

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void send(String type, Object data) {
        try {
            String jsonData = objectMapper.writeValueAsString(data);

            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.EXCHANGE_NAME,
                    type,
                    jsonData,
                    message -> {
                        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                        return message;
                    }
            );
        } catch (Exception ex) {
            throw new RuntimeException("Error serializing message: " + ex.getMessage(), ex);
        }
    }
}
