package com.beautique.api.controller;

import com.beautique.api.dto.AppointmentRequestDTO;
import com.beautique.api.dto.AppointmentResponseDTO;
import com.beautique.api.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping
    public List<AppointmentResponseDTO> findAll() {
        return appointmentService.findAll();
    }

    @GetMapping("/{id}")
    public AppointmentResponseDTO findById(@PathVariable Long id) {
        return appointmentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentResponseDTO create(@RequestBody @Valid AppointmentRequestDTO appointmentRequestDTO) {
        return appointmentService.create(appointmentRequestDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponseDTO update(@PathVariable Long id, @RequestBody @Valid AppointmentRequestDTO appointmentRequestDTO) {
        return appointmentService.update(id, appointmentRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        appointmentService.delete(id);
    }
}
