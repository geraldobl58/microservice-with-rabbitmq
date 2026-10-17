package com.beautique.ms_sync.dto;

import java.math.BigDecimal;

public record ProcedureDTO(
        Long id,
        String name,
        String description,
        BigDecimal price
) {
}
