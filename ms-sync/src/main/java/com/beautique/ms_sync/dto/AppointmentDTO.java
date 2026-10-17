package com.beautique.ms_sync.dto;

import java.time.LocalDateTime;

public record AppointmentDTO(
        Long id,
        LocalDateTime dateTime,
        Boolean appointmentOpen,
        CustomerDTO customer,
        ProcedureDTO procedure
) {
}
