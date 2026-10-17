package com.beautique.api.dto;


import java.time.LocalDateTime;

public record AppointmentMessageDTO(
        Long id,
        LocalDateTime dateTime,
        Boolean appointmentOpen,
        CustomerRequestDTO customer,
        ProcedureRequestDTO procedure
) {
}
