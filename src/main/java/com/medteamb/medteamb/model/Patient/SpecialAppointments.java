package com.medteamb.medteamb.model.Patient;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Entity
public class SpecialAppointments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    @Column(name = "details")
    private String details;
    @Column(name = "richiesto_il_giorno")
    private LocalDateTime emissionDate = LocalDateTime.now();
    @Column(name = "data")
    private LocalDate appointmentDate;
    @Column(name = "ora")
    private LocalTime appointmentHour;
    @Column(name = "stato_richiesta")
    private State stato = State.TO_BE_CONFIRMED;

            private enum State{
                TO_BE_CONFIRMED,
                EXPIRED,
                CANCELLED,
                APPROVED
            }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getEmissionDate() {
        return emissionDate;
    }

    public void setEmissionDate(LocalDateTime emissionDate) {
        this.emissionDate = emissionDate;
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

    public State getStato() {
        return stato;
    }

    public void setStato(State stato) {
        this.stato = stato;
    }
}
