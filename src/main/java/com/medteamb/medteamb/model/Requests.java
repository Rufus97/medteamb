package com.medteamb.medteamb.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Requests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @Column(name = "date")
    private LocalDate day;
    @Column(name = "hour")
    private LocalTime hour;
    @Column(name = "description")
    private String description;
    @Column(name = "new_date")
    private LocalDate newDate;
    @Column(name = "state")
    private RequestState state = RequestState.TO_BE_APPROVED;



    private enum RequestState{
        TO_BE_APPROVED,
        APPROVED,
        TO_BE_MOVED
    }
    public void appointmentToBeMoved(){
        this.state = RequestState.TO_BE_MOVED;
    }

    public void appointmentApproved(){
        this.state = RequestState.APPROVED;
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

    public RequestState getState() {
        return state;
    }

    public void setState(RequestState state) {
        this.state = state;
    }

    public LocalDate getNewDate() {
        return newDate;
    }

    public void setNewDate(LocalDate newDate) {
        this.newDate = newDate;
    }
}
