package com.beautique.api.service;

public interface BrokerService {
    void send(String routingKey, Object data);
}
