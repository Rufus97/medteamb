package com.medteamb.medteamb.service.dto.patient.AppointmentSlots;

import com.medteamb.medteamb.model.AppointmentStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public class AvaibleAppointmentResponseDTO {

    private Long id;
    private LocalTime minHour;
    private Long doctor_id;
    private LocalDate today;
    private AppointmentStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getMinHour() {
        return minHour;
    }

    public void setMinHour(LocalTime minHour) {
        this.minHour = minHour;
    }

    public Long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Long doctor_id) {
        this.doctor_id = doctor_id;
    }



    public LocalDate getToday() {
        return today;
    }

    public void setToday(LocalDate today) {
        this.today = today;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}
