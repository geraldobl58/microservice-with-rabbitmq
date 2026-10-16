package com.beautique.api.service.impl;

import com.beautique.api.dto.ProcedureRequestDTO;
import com.beautique.api.dto.ProcedureResponseDTO;
import com.beautique.api.exception.ResourceNotFoundException;
import com.beautique.api.mapper.ProcedureMapper;
import com.beautique.api.model.Procedure;
import com.beautique.api.repository.ProcedureRepository;
import com.beautique.api.service.BrokerService;
import com.beautique.api.service.ProcedureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProcedureServiceImpl implements ProcedureService {

    private final ProcedureRepository procedureRepository;
    private final ProcedureMapper procedureMapper;
    private final BrokerService brokerService;

    @Override
    public ProcedureResponseDTO create(ProcedureRequestDTO procedureRequestDTO) {
        Procedure procedure = procedureMapper.toEntity(procedureRequestDTO);
        Procedure savedProcedure = procedureRepository.save(procedure);

        sendProcedure("created", toRequestDTO(savedProcedure));

        return procedureMapper.toResponseDTO(savedProcedure);
    }

    @Override
    public ProcedureResponseDTO update(Long id, ProcedureRequestDTO procedureDTO) {
        Procedure procedure = procedureRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Procedure", "id", id));

        procedureMapper.updateProcedureFromRequest(procedureDTO, procedure);
        Procedure updatedProcedure = procedureRepository.save(procedure);

        sendProcedure("updated", toRequestDTO(updatedProcedure));

        return procedureMapper.toResponseDTO(updatedProcedure);
    }

    @Override
    public void delete(Long id) {
        if (!procedureRepository.existsById(id)) {
            throw new ResourceNotFoundException("Procedure", "id", id);
        }

        procedureRepository.deleteById(id);
    }

    private void sendProcedure(String event, ProcedureRequestDTO procedureRequestDTO) {
        brokerService.send("procedure." + event, procedureRequestDTO);
    }

    private ProcedureRequestDTO toRequestDTO(Procedure procedure) {
        return new ProcedureRequestDTO(
                procedure.getId(),
                procedure.getName(),
                procedure.getDescription(),
                procedure.getPrice()
        );
    }
}
