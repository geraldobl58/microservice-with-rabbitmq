package com.beautique.api.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentRequestDTO(
        Long id,

        @NotNull(message = "A data/hora é obrigatória")
        LocalDateTime dateTime,

        @NotNull(message = "O status do agendamento é obrigatório")
        Boolean appointmentOpen,

        @NotNull(message = "O cliente é obrigatório")
        Long customer,

        @NotNull(message = "O procedimento é obrigatório")
        Long procedure
) {
}
