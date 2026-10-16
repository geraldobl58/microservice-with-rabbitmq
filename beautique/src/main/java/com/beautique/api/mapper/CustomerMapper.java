package com.beautique.api.mapper;

import com.beautique.api.dto.CustomerRequestDTO;
import com.beautique.api.dto.CustomerResponseDTO;
import com.beautique.api.model.Customer;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "appointments", ignore = true)
    Customer toEntity(CustomerRequestDTO requestDTO);

    CustomerResponseDTO toResponseDTO(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "appointments", ignore = true)
    void updateCustomerFromRequest(CustomerRequestDTO customerRequestDTO, @MappingTarget Customer customer);
}