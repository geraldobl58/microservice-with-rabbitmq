package com.beautique.api.service.impl;

import com.beautique.api.dto.AppointmentRequestDTO;
import com.beautique.api.dto.AppointmentResponseDTO;
import com.beautique.api.exception.ResourceNotFoundException;
import com.beautique.api.mapper.AppointmentMapper;
import com.beautique.api.model.Appointment;
import com.beautique.api.model.Customer;
import com.beautique.api.model.Procedure;
import com.beautique.api.repository.AppointmentRepository;
import com.beautique.api.repository.CustomerRepository;
import com.beautique.api.repository.ProcedureRepository;
import com.beautique.api.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final ProcedureRepository procedureRepository;
    private final CustomerRepository customerRepository;
    private final AppointmentMapper appointmentMapper;

    @Override
    public List<AppointmentResponseDTO> findAll() {
        return appointmentRepository.findAll()
                .stream()
                .map(appointmentMapper::toResponseDTO)
                .toList();
    }

    @Override
    public AppointmentResponseDTO findById(Long id) {
        return appointmentMapper.toResponseDTO(findAppointmentById(id));
    }

    @Override
    public AppointmentResponseDTO create(AppointmentRequestDTO appointmentRequestDTO) {
        Appointment appointment = buildAppointment(appointmentRequestDTO);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.toResponseDTO(savedAppointment);
    }

    @Override
    public AppointmentResponseDTO update(Long id, AppointmentRequestDTO appointmentDTO) {
        Appointment appointment = findAppointmentById(id);

        Customer customer = findCustomerById(appointmentDTO.customer());
        Procedure procedure = findProcedureById(appointmentDTO.procedure());

        appointment.setDateTime(appointmentDTO.dateTime());
        appointment.setAppointmentOpen(appointmentDTO.appointmentOpen());
        appointment.setCustomer(customer);
        appointment.setProcedure(procedure);

        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.toResponseDTO(updatedAppointment);
    }

    @Override
    public void delete(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Appointment", "id", id);
        }

        appointmentRepository.deleteById(id);
    }

    @Override
    public AppointmentResponseDTO setCustomerToAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        return null;
    }

    private Appointment findAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "id", id));
    }

    private Procedure findProcedureById(Long id) {
        return procedureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Procedure", "id", id));
    }

    private Customer findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
    }

    private Appointment buildAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        Appointment appointment = new Appointment();
        appointment.setDateTime(appointmentRequestDTO.dateTime());
        appointment.setAppointmentOpen(appointmentRequestDTO.appointmentOpen());
        appointment.setCustomer(findCustomerById(appointmentRequestDTO.customer()));
        appointment.setProcedure(findProcedureById(appointmentRequestDTO.procedure()));
        return appointment;
    }
}
