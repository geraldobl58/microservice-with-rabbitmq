package com.beautique.ms_sync.listners.impl;

import com.beautique.ms_sync.dto.AppointmentDTO;
import com.beautique.ms_sync.dto.CustomerDTO;
import com.beautique.ms_sync.dto.ProcedureDTO;
import com.beautique.ms_sync.listners.ListenerConfig;
import com.beautique.ms_sync.service.SyncService;
import com.beautique.ms_sync.utils.SyncLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ObjectMapper;

@Configuration
@EnableRabbit
@RequiredArgsConstructor
public class RabbitMQListenerConfig implements ListenerConfig {
    private final ObjectMapper objectMapper;
    private final SyncService syncService;

    @Override
    @RabbitListener(queues = "customerQueue")
    public void listenerToCustomerQueue(String message) {
        try {
            CustomerDTO customer = objectMapper.readValue(message, CustomerDTO.class);
            syncService.syncCustomer(customer);
            SyncLogger.info("Message received from queue customer: " + customer.toString());
        } catch (Exception ex) {
            SyncLogger.error("Error listener customer queue: " + ex.getMessage());
        }
    }

    @Override
    @RabbitListener(queues = "procedureQueue")
    public void listenerToProcedureQueue(String message) {
        try {
            ProcedureDTO procedure = objectMapper.readValue(message, ProcedureDTO.class);
            syncService.syncProcedure(procedure);
            SyncLogger.info("Message received from queue procedure: " + procedure.toString());
        } catch (Exception ex) {
            SyncLogger.error("Error listener procedure queue: " + ex.getMessage());
        }
    }

    @Override
    @RabbitListener(queues = "appointmentQueue")
    public void listenerToAppointmentQueue(String message) {
        try {
            AppointmentDTO appointment = objectMapper.readValue(message, AppointmentDTO.class);
            syncService.syncAppointment(appointment);
            SyncLogger.info("Message received from queue appointment: " + appointment.toString());
        } catch (Exception ex) {
            SyncLogger.error("Error listener appointment queue: " + ex.getMessage());
        }
    }
}
