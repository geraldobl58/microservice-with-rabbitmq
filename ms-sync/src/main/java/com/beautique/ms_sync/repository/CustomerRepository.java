package com.beautique.ms_sync.repository;

import com.beautique.ms_sync.dto.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerDTO, Long> {
}
