package com.medteamb.medteamb.service.dto.patient;

import java.time.LocalDate;
import java.time.LocalTime;

public class PatientAppointmentRequestDTO {

    private LocalDate data;
    private LocalTime hour;


    public PatientAppointmentRequestDTO(LocalDate data, LocalTime hour) {
        this.data = data;
        this.hour = hour;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }


}