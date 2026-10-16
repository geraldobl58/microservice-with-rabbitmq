package com.beautique.api.service;

import com.beautique.api.dto.CustomerRequestDTO;
import com.beautique.api.dto.CustomerResponseDTO;

public interface CustomerService {
    CustomerResponseDTO create(CustomerRequestDTO customerDto);
    CustomerResponseDTO update(Long id, CustomerRequestDTO customerDto);
    void delete(Long id);
}
