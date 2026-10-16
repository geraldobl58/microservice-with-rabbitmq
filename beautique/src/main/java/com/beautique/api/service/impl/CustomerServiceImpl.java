package com.beautique.api.service.impl;

import com.beautique.api.dto.CustomerRequestDTO;
import com.beautique.api.dto.CustomerResponseDTO;
import com.beautique.api.exception.ResourceNotFoundException;
import com.beautique.api.mapper.CustomerMapper;
import com.beautique.api.model.Customer;
import com.beautique.api.repository.CustomerRepository;
import com.beautique.api.service.BrokerService;
import com.beautique.api.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final BrokerService brokerService;

    @Override
    public CustomerResponseDTO create(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.toEntity(customerRequestDTO);
        Customer savedCustomer = customerRepository.save(customer);

        sendCustomer("created", toRequestDTO(savedCustomer));

        return customerMapper.toResponseDTO(savedCustomer);
    }

    @Override
    public CustomerResponseDTO update(Long id, CustomerRequestDTO customerDto) {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        customerMapper.updateCustomerFromRequest(customerDto, customer);
        Customer updatedCustomer = customerRepository.save(customer);

        sendCustomer("updated", toRequestDTO(updatedCustomer));

        return customerMapper.toResponseDTO(updatedCustomer);
    }


    @Override
    public void delete(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer", "id", id);
        }

        customerRepository.deleteById(id);
    }

    private void sendCustomer(String event, CustomerRequestDTO customerRequestDTO) {
        brokerService.send("customer." + event, customerRequestDTO);
    }

    private CustomerRequestDTO toRequestDTO(Customer customer) {
        return new CustomerRequestDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone()
        );
    }
}
