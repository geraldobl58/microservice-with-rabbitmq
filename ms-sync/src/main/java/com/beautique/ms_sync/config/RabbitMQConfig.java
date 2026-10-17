package com.beautique.ms_sync.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "beautifulExchange";

    public static final String CUSTOMER_QUEUE = "customerQueue";
    public static final String CUSTOMER_ROUTING_KEY = "customer.#";

    public static final String PROCEDURE_QUEUE = "procedureQueue";
    public static final String PROCEDURE_ROUTING_KEY = "procedure.#";

    public static final String APPOINTMENT_QUEUE = "appointmentQueue";
    public static final String APPOINTMENT_ROUTING_KEY = "appointment.#";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME, true, false);
    }

    @Bean
    public Queue customerQueue() {
        return new Queue(CUSTOMER_QUEUE, true);
    }

    @Bean
    public Binding customerBinding(Queue customerQueue, TopicExchange exchange) {
        return BindingBuilder.bind(customerQueue).to(exchange).with(CUSTOMER_ROUTING_KEY);
    }

    @Bean
    public Queue procedureQueue() {
        return new Queue(PROCEDURE_QUEUE, true);
    }

    @Bean
    public Binding procedureBinding(Queue procedureQueue, TopicExchange exchange) {
        return BindingBuilder.bind(procedureQueue).to(exchange).with(PROCEDURE_ROUTING_KEY);
    }

    @Bean
    public Queue appointmentQueue() {
        return new Queue(APPOINTMENT_QUEUE, true);
    }

    @Bean
    public Binding appointmentBinding(Queue appointmentQueue, TopicExchange exchange) {
        return BindingBuilder.bind(appointmentQueue).to(exchange).with(APPOINTMENT_ROUTING_KEY);
    }
}
