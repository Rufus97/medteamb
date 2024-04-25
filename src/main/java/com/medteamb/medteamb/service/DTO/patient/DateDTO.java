package com.medteamb.medteamb.service.dto.patient;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateDTO {

    LocalDateTime date = LocalDateTime.now();

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
