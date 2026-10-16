package com.beautique.api.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;

@JsonPropertyOrder({"id", "name", "phone", "email", "createdAt", "updatedAt"})
public record CustomerResponseDTO(
        Long id,
        String name,
        String phone,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
