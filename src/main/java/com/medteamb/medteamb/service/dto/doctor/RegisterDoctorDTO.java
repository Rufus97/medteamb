package com.medteamb.medteamb.service.dto.doctor;

import com.medteamb.medteamb.model.Specialization;
import jakarta.annotation.Nullable;

import java.time.LocalTime;

public class RegisterDoctorDTO {

    private String doctorName;
    private String doctorSurname;
    private String doctorPhoneNumber;
    private String doctorEmail;
    private Specialization specialization;
    private LocalTime beginningWorkTime;
    private Integer appointmentsDuration;
    private Integer appointmentsPerDay;
    private Integer agendaMonthsRange;

    private String username;
    private String password;
    private String specialToken;

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSurname() {
        return doctorSurname;
    }

    public void setDoctorSurname(String doctorSurname) {
        this.doctorSurname = doctorSurname;
    }

    public String getDoctorPhoneNumber() {
        return doctorPhoneNumber;
    }

    public void setDoctorPhoneNumber(String doctorPhoneNumber) {
        this.doctorPhoneNumber = doctorPhoneNumber;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public LocalTime getBeginningWorkTime() {
        return beginningWorkTime;
    }

    public void setBeginningWorkTime(LocalTime beginningWorkTime) {
        this.beginningWorkTime = beginningWorkTime;
    }

    public Integer getAppointmentsDuration() {
        return appointmentsDuration;
    }

    public void setAppointmentsDuration(Integer appointmentsDuration) {
        this.appointmentsDuration = appointmentsDuration;
    }

    public Integer getAppointmentsPerDay() {
        return appointmentsPerDay;
    }

    public void setAppointmentsPerDay(Integer appointmentsPerDay) {
        this.appointmentsPerDay = appointmentsPerDay;
    }

    public Integer getAgendaMonthsRange() {
        return agendaMonthsRange;
    }

    public void setAgendaMonthsRange(Integer agendaMonthsRange) {
        this.agendaMonthsRange = agendaMonthsRange;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSpecialToken() {
        return specialToken;
    }

    public void setSpecialToken(String specialToken) {
        this.specialToken = specialToken;
    }
}
