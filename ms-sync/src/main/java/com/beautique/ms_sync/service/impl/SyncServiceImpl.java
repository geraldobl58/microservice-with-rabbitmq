package com.beautique.ms_sync.service.impl;

import com.beautique.ms_sync.dto.AppointmentDTO;
import com.beautique.ms_sync.dto.CustomerDTO;
import com.beautique.ms_sync.dto.ProcedureDTO;
import com.beautique.ms_sync.repository.AppointmentRepository;
import com.beautique.ms_sync.service.AppointmentService;
import com.beautique.ms_sync.service.CustomerService;
import com.beautique.ms_sync.service.ProcedureService;
import com.beautique.ms_sync.service.SyncService;
import com.beautique.ms_sync.utils.SyncLogger;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class SyncServiceImpl implements SyncService {
    private final AppointmentService appointmentService;
    private final CustomerService customerService;
    private final ProcedureService procedureService;

    @Override
    public void syncCustomer(CustomerDTO customerDTO) {
        try {
            SyncLogger.info("Saving customer: ".concat(customerDTO.name()));
            customerService.saveCustomer(customerDTO);
            SyncLogger.info("Updating appointment customer: ".concat(customerDTO.name()));
            appointmentService.updateAppointmentCustomer(customerDTO);
        } catch (Exception ex) {
            SyncLogger.error("Error saving customer: " + customerDTO.name());
            SyncLogger.trace(Arrays.toString(ex.getStackTrace()));
        }
    }

    @Override
    public void syncAppointment(AppointmentDTO appointmentDTO) {
        try {
            SyncLogger.info("Saving appointment: " + appointmentDTO.id());
            appointmentService.createAppointment(appointmentDTO);
        } catch (Exception ex) {
            SyncLogger.error("Error saving appointment: " + appointmentDTO.id());
            SyncLogger.trace(Arrays.toString(ex.getStackTrace()));
        }
    }

    @Override
    public void syncProcedure(ProcedureDTO procedureDTO) {
        try {
            SyncLogger.info("Saving procedure: ".concat(procedureDTO.name()));
            procedureService.saveProcedure(procedureDTO);
            SyncLogger.info("Updating procedure: ".concat(procedureDTO.name()));
            appointmentService.updateAppointmentProcedure(procedureDTO);
        } catch (Exception ex) {
            SyncLogger.error("Error saving procedure: ".concat(procedureDTO.name()));
            SyncLogger.trace(Arrays.toString(ex.getStackTrace()));
        }
    }
}
