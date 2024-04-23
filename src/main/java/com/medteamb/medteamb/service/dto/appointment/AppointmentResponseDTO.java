package com.medteamb.medteamb.service.dto.appointment;

import java.time.LocalDateTime;

import com.medteamb.medteamb.model.AppointmentStatus;

public class AppointmentResponseDTO {
	
	private Integer appointmentID;
	private LocalDateTime appointmentDateTime;
	private AppointmentStatus status;
	private String medicalService;
	private String location;
	private Integer doctor;
	private Integer secretary;
	private Integer patient;
	
	public Integer getAppointmentID() {
		return appointmentID;
	}
	
	public void setAppointmentID(Integer appointmentID) {
		this.appointmentID = appointmentID;
	}
	
	public Integer getPatient() {
		return patient;
	}

	public void setPatient(Integer integer) {
		this.patient = integer;
	}

	public LocalDateTime getAppointmentDateTime() {
		return appointmentDateTime;
	}

	public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
		this.appointmentDateTime = appointmentDateTime;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}

	public String getMedicalService() {
		return medicalService;
	}

	public void setMedicalService(String medicalService) {
		this.medicalService = medicalService;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getDoctor() {
		return doctor;
	}

	public void setDoctor(Integer integer) {
		this.doctor = integer;
	}

	public Integer getSecretary() {
		return secretary;
	}

	public void setSecretary(Integer integer) {
		this.secretary = integer;
	}
}
