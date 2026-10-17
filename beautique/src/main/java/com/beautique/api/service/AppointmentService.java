package com.beautique.api.service;

import com.beautique.api.dto.AppointmentRequestDTO;
import com.beautique.api.dto.AppointmentResponseDTO;

import java.util.List;

public interface AppointmentService {
    List<AppointmentResponseDTO> findAll();
    AppointmentResponseDTO findById(Long id);
    AppointmentResponseDTO create(AppointmentRequestDTO appointmentRequestDTO);
    AppointmentResponseDTO update(Long id, AppointmentRequestDTO appointmentRequestDTO);
    void delete(Long id);
}
