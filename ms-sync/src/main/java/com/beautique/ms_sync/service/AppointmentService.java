package com.beautique.ms_sync.service;

import com.beautique.ms_sync.dto.AppointmentDTO;
import com.beautique.ms_sync.dto.CustomerDTO;
import com.beautique.ms_sync.dto.ProcedureDTO;

public interface AppointmentService {
    void createAppointment(AppointmentDTO appointmentDTO);
    void updateAppointmentCustomer(CustomerDTO customerDTO);
    void updateAppointmentProcedure(ProcedureDTO procedureDTO);
}
