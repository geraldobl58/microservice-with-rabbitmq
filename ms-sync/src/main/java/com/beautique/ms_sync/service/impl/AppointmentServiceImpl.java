package com.beautique.ms_sync.service.impl;

import com.beautique.ms_sync.dto.AppointmentDTO;
import com.beautique.ms_sync.dto.CustomerDTO;
import com.beautique.ms_sync.dto.ProcedureDTO;
import com.beautique.ms_sync.repository.AppointmentRepository;
import com.beautique.ms_sync.service.AppointmentService;
import com.beautique.ms_sync.utils.SyncLogger;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    @Override
    public void createAppointment(AppointmentDTO appointmentDTO) {
        try {
            SyncLogger.info("Saving appointment: " + appointmentDTO.procedure());
            appointmentRepository.save(appointmentDTO);
        } catch (Exception ex) {
            SyncLogger.error("Error saving appointment: ".concat(ex.getMessage()));
            SyncLogger.trace(Arrays.toString(ex.getStackTrace()));
        }
    }

    @Override
    public void updateAppointmentCustomer(CustomerDTO customerDTO) {
        try {
            if (customerDTO == null || customerDTO.id() == null) {
                return;
            }

            List<AppointmentDTO> appointmentsToUpdate = appointmentRepository.findAll()
                    .stream()
                    .filter(appointment -> appointment != null
                            && appointment.customer() != null
                            && customerDTO.id().equals(appointment.customer().id()))
                    .map(appointment -> new AppointmentDTO(
                            appointment.id(),
                            appointment.dateTime(),
                            appointment.appointmentOpen(),
                            customerDTO,
                            appointment.procedure()))
                    .toList();

            if (!appointmentsToUpdate.isEmpty()) {
                appointmentRepository.saveAll(appointmentsToUpdate);
            }
        } catch (Exception ex) {
            SyncLogger.error("Error updating appointment customer: ".concat(ex.getMessage()));
            SyncLogger.trace(Arrays.toString(ex.getStackTrace()));
        }
    }

    @Override
    public void updateAppointmentProcedure(ProcedureDTO procedureDTO) {
        try {
            if (procedureDTO == null || procedureDTO.id() == null) {
                return;
            }

            List<AppointmentDTO> appointmentsToUpdate = appointmentRepository.findAll()
                    .stream()
                    .filter(appointment -> appointment != null
                            && appointment.procedure() != null
                            && procedureDTO.id().equals(appointment.procedure().id()))
                    .map(appointment -> new AppointmentDTO(
                            appointment.id(),
                            appointment.dateTime(),
                            appointment.appointmentOpen(),
                            appointment.customer(),
                            procedureDTO))
                    .toList();

            if (!appointmentsToUpdate.isEmpty()) {
                appointmentRepository.saveAll(appointmentsToUpdate);
            }
        } catch (Exception ex) {
            SyncLogger.error("Error updating appointment procedure: ".concat(ex.getMessage()));
            SyncLogger.trace(Arrays.toString(ex.getStackTrace()));
        }
    }
}
