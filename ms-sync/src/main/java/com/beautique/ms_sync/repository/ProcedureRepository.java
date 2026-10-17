package com.beautique.ms_sync.repository;

import com.beautique.ms_sync.dto.ProcedureDTO;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProcedureRepository extends JpaRepository<ProcedureDTO, Long> {
}
