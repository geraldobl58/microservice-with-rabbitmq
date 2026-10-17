package com.beautique.ms_sync.listners;

public interface ListenerConfig {
    void listenerToCustomerQueue(String message);
    void listenerToProcedureQueue(String message);
    void listenerToAppointmentQueue(String message);
}
