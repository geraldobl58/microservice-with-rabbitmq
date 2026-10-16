package com.beautique.api.service;

import com.beautique.api.dto.ProcedureRequestDTO;
import com.beautique.api.dto.ProcedureResponseDTO;

public interface ProcedureService {
    ProcedureResponseDTO create(ProcedureRequestDTO procedureRequestDTO);
    ProcedureResponseDTO update(Long id, ProcedureRequestDTO procedureRequestDTO);
    void delete(Long id);
}
