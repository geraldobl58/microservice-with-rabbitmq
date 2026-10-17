package com.beautique.ms_sync.service.impl;

import com.beautique.ms_sync.dto.CustomerDTO;
import com.beautique.ms_sync.repository.CustomerRepository;
import com.beautique.ms_sync.service.CustomerService;
import com.beautique.ms_sync.utils.SyncLogger;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        try {
            SyncLogger.info("Saving customer: ".concat(customerDTO.name()));
            customerRepository.save(customerDTO);
        } catch (Exception ex) {
            SyncLogger.error("Error saving customer: " + ex.getMessage());
            SyncLogger.trace(Arrays.toString(ex.getStackTrace()));
        }
    }
}
