package com.medteamb.medteamb.service.dto.patient.PatientAppointmentDTO;

import com.medteamb.medteamb.model.Patient.Patient;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalTime;

public class ResponseForNewAppointmentDTO {

    private Long id;
    private Long patient_id;
    private LocalDate day;
    private LocalTime hour;
    private String description;

    public ResponseForNewAppointmentDTO(Long id, Long patient_id, LocalDate day, LocalTime hour, String description) {
        this.id = id;
        this.patient_id = patient_id;
        this.day = day;
        this.hour = hour;
        this.description = description;
    }

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

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
