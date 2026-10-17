package com.beautique.ms_sync.repository;

import com.beautique.ms_sync.dto.AppointmentDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<AppointmentDTO, Long> {
}
