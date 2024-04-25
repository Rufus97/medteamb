package com.medteamb.medteamb.service.dto.patient.PatientAppointmentDTO;

import java.time.LocalDate;

public class RequestToCancelAppointmentDTO {

    private LocalDate date;
    private String description;


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
