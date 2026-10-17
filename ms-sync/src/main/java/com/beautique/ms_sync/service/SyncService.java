package com.beautique.ms_sync.service;

import com.beautique.ms_sync.dto.AppointmentDTO;
import com.beautique.ms_sync.dto.CustomerDTO;
import com.beautique.ms_sync.dto.ProcedureDTO;

public interface SyncService {
    void syncCustomer(CustomerDTO customerDTO);
    void syncAppointment(AppointmentDTO appointmentDTO);
    void syncProcedure(ProcedureDTO procedureDTO);
}
