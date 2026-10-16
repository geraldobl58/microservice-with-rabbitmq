package com.beautique.api.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record AppointmentResponseDTO(
        Long id,
        LocalDateTime dateTime,
        Boolean appointmentOpen,
        Long customer,
        Long procedure
) {
}
