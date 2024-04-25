package com.medteamb.medteamb.service.dto.patient.PatientAppointmentDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PatientRequestAppointmentDTO {

    Integer patientID;
    LocalDateTime appointmentDate;
    String message;

    public PatientRequestAppointmentDTO(Integer patientID, LocalDateTime appointmentDate, String message) {
        this.patientID = patientID;
        this.appointmentDate = appointmentDate;
        this.message = message;
    }

    public Integer getPatientID() {
        return patientID;
    }

    public void setPatientID(Integer patientID) {
        this.patientID = patientID;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
