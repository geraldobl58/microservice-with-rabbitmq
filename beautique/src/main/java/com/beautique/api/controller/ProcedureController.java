package com.beautique.api.controller;

import com.beautique.api.dto.ProcedureRequestDTO;
import com.beautique.api.dto.ProcedureResponseDTO;
import com.beautique.api.service.ProcedureService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/procedure")
public class ProcedureController {

    private final ProcedureService procedureService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProcedureResponseDTO create(@RequestBody @Valid ProcedureRequestDTO procedureRequestDTO) {
        return procedureService.create(procedureRequestDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProcedureResponseDTO update(@PathVariable Long id, @RequestBody @Valid ProcedureRequestDTO procedureRequestDTO) {
        return procedureService.update(id, procedureRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        procedureService.delete(id);
    }

}
