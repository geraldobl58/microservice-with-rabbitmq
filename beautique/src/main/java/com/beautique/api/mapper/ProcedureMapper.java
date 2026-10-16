package com.beautique.api.mapper;

import com.beautique.api.dto.ProcedureRequestDTO;
import com.beautique.api.dto.ProcedureResponseDTO;
import com.beautique.api.model.Procedure;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProcedureMapper {

    @Mapping(target = "appointments", ignore = true)
    Procedure toEntity(ProcedureRequestDTO requestDTO);

    ProcedureResponseDTO toResponseDTO(Procedure procedure);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "appointments", ignore = true)
    void updateProcedureFromRequest(ProcedureRequestDTO procedureRequestDTO, @MappingTarget Procedure procedure);
}
