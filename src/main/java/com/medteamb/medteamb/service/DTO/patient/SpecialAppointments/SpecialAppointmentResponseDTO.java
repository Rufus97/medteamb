package com.medteamb.medteamb.service.dto.patient.SpecialAppointments;


import java.time.LocalDate;
import java.time.LocalTime;

public class SpecialAppointmentResponseDTO {

    private Long id;
    private Long patient_id;
    private LocalDate appointmentDate;
    private LocalTime appointmentHour;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Long patient_id) {
        this.patient_id = patient_id;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentHour() {
        return appointmentHour;
    }

    public void setAppointmentHour(LocalTime appointmentHour) {
        this.appointmentHour = appointmentHour;
    }
}
