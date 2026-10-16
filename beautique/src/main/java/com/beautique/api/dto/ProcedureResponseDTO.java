package com.beautique.api.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

@JsonPropertyOrder({"id", "name", "description", "price", "createdAt", "updatedAt"})
public record ProcedureResponseDTO(
        Long id,
        String name,
        String description,
        BigDecimal price
) {
}
