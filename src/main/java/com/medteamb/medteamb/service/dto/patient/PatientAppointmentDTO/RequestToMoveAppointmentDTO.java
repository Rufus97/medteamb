package com.medteamb.medteamb.service.dto.patient.PatientAppointmentDTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class RequestToMoveAppointmentDTO {


    private Long patient_id;
    private LocalDate day;
    private LocalTime hour;
    private String description;
    private LocalDate newDay;

    private LocalTime newHour;





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

    public LocalDate getNewDay() {
        return newDay;
    }

    public void setNewDay(LocalDate newDay) {
        this.newDay = newDay;
    }

    public LocalTime getNewHour() {
        return newHour;
    }

    public void setNewHour(LocalTime newHour) {
        this.newHour = newHour;
    }
}
