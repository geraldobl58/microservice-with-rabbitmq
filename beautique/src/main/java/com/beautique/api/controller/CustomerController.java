package com.beautique.api.controller;

import com.beautique.api.dto.CustomerRequestDTO;
import com.beautique.api.dto.CustomerResponseDTO;
import com.beautique.api.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponseDTO create(@RequestBody @Valid CustomerRequestDTO customerDto) {
        return customerService.create(customerDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponseDTO update(@PathVariable Long id, @RequestBody @Valid CustomerRequestDTO customerRequestDTO) {
        return customerService.update(id, customerRequestDTO);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }
}