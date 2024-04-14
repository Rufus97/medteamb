package com.medteamb.medteamb.model.Calendar;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "slot_appuntamento")
public class AppointmentSlot {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "orario iniziale", nullable = false)
    private LocalTime minHour;
    @Column(name = "orario finale", nullable = false)
    private LocalTime maxHour;
    @Column(name = "giorno ")
    private LocalDate today;
    private Boolean isPrenotated = false;


    public AppointmentSlot(  LocalDate today, LocalTime orarioMinimo, LocalTime maxHour) {
        this.minHour = orarioMinimo;
        this.maxHour = maxHour;
        this.today = today;
    }

    public Integer getId() {
        return id;
    }

    public LocalTime getMinHour() {
        return minHour;
    }

    public void setMinHour(LocalTime minHour) {
        this.minHour = minHour;
    }

    public LocalTime getMaxHour() {
        return maxHour;
    }

    public void setMaxHour(LocalTime maxHour) {
        this.maxHour = maxHour;
    }

    public LocalDate getToday() {
        return today;
    }

    public void setToday(LocalDate today) {
        this.today = today;
    }

    public Boolean getPrenotated() {
        return isPrenotated;
    }

    public void setPrenotated(Boolean prenotated) {
        isPrenotated = prenotated;
    }

    public void setAppointment(){
        this.isPrenotated = true;
    }
    public void cancelAppointment(){
        this.isPrenotated = false;
    }

    @Override
    public String toString() {
        return " da:" + minHour +
                " a:" + maxHour;
    }
}
