package com.medteamb.medteamb.service.dto.patient.PatientAppointmentDTO;

import java.time.LocalDateTime;

public class PatientResponseAppointmentDTO {
    Integer doctorID;
    Integer patientID;
    LocalDateTime appointmentDate;
    String message;

    public Integer getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Integer doctorID) {
        this.doctorID = doctorID;
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
