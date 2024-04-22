package com.medteamb.medteamb.model.Calendar;

import com.medteamb.medteamb.model.AppointmentStatus;
import com.medteamb.medteamb.model.Doctor;
import com.medteamb.medteamb.model.Patient;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "slot_appuntamento")
public class AppointmentSlot {
    @Id
    
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "orario_iniziale", nullable = false)
    private LocalTime minHour;
    @JoinColumn(name = "doctor_id", nullable = false)
    @ManyToOne
    private Doctor doctor_id;
    @JoinColumn(name = "patient_id")
    @ManyToOne
    private Patient patient_id;
    @Column(name = "giorno")
    private LocalDate today;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status = AppointmentStatus.AVAIBLE;


    public AppointmentSlot(){};


    public AppointmentSlot(  LocalDate today, LocalTime orarioMinimo,  Doctor doctor_id) {
        this.minHour = orarioMinimo;
        this.today = today;
        this.doctor_id = doctor_id;
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

    public LocalDate getToday() {
        return today;
    }

    public void setToday(LocalDate today) {
        this.today = today;
    }

    public Doctor getDoctor_id() {
        return doctor_id;
    }

    public Patient getPatient_id() {
        return patient_id;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "AppointmentSlot{" +
                "id=" + id +
                ", minHour=" + minHour +
                ", doctor_id=" + doctor_id +
                ", patient_id=" + patient_id +
                ", today=" + today +
                ", status=" + status +
                '}';
    }
}
