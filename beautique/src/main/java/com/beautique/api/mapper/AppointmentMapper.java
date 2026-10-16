package com.beautique.api.mapper;

import com.beautique.api.dto.AppointmentRequestDTO;
import com.beautique.api.dto.AppointmentResponseDTO;
import com.beautique.api.model.Appointment;
import com.beautique.api.model.Customer;
import com.beautique.api.model.Procedure;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mapping(target = "customer", source = "customer", qualifiedByName = "customerFromId")
    @Mapping(target = "procedure", source = "procedure", qualifiedByName = "procedureFromId")
    Appointment toEntity(AppointmentRequestDTO requestDTO);

    @Mapping(target = "customer", source = "customer.id")
    @Mapping(target = "procedure", source = "procedure.id")
    AppointmentResponseDTO toResponseDTO(Appointment appointment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", source = "customer", qualifiedByName = "customerFromId")
    @Mapping(target = "procedure", source = "procedure", qualifiedByName = "procedureFromId")
    void updateAppointmentFromRequest(AppointmentRequestDTO appointmentRequestDTO, @MappingTarget Appointment appointment);

    @Named("customerFromId")
    default Customer customerFromId(Long customerId) {
        if (customerId == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setId(customerId);
        return customer;
    }

    @Named("procedureFromId")
    default Procedure procedureFromId(Long procedureId) {
        if (procedureId == null) {
            return null;
        }

        Procedure procedure = new Procedure();
        procedure.setId(procedureId);
        return procedure;
    }
}
