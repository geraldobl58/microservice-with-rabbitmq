package com.beautique.ms_sync.service.impl;

import com.beautique.ms_sync.dto.ProcedureDTO;
import com.beautique.ms_sync.repository.ProcedureRepository;
import com.beautique.ms_sync.service.ProcedureService;
import com.beautique.ms_sync.utils.SyncLogger;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class ProcedureServiceImpl implements ProcedureService {
    private final ProcedureRepository procedureRepository;

    @Override
    public void saveProcedure(ProcedureDTO procedureDTO) {
        try {
            SyncLogger.info("Saving procedure: ".concat(procedureDTO.name()));
            procedureRepository.save(procedureDTO);
        } catch (Exception ex) {
            SyncLogger.error("Error saving procedure: " + ex.getMessage());
            SyncLogger.trace(Arrays.toString(ex.getStackTrace()));
        }
    }
}
