package com.medteamb.medteamb.service.dto.patient.SpecialAppointments;

import java.time.LocalDate;
import java.time.LocalTime;

public class SpecialAppointmentRequestDTO {

    private Long patientID;
    private String details;
    private LocalDate appointmentDate;
    private LocalTime appointmentHour;


    public Long getPatientID() {
        return this.patientID;
    }

    public void setPatient(Long patient) {
        this.patientID = patient;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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
