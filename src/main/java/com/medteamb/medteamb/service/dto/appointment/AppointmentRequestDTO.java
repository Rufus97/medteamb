package com.medteamb.medteamb.service.dto.appointment;

import java.time.LocalDateTime;

import com.medteamb.medteamb.model.agenda.AppointmentStatus;

public class AppointmentRequestDTO {
	
	private LocalDateTime appointmentDateTime;
	private AppointmentStatus status;
	private String medicalService;
	private String location;
	private String taxCode;
	private Integer doctor;
	private Integer secretary;
	private Long patient;
	
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

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public Integer getDoctor() {
		return doctor;
	}

	public void setDoctor(Integer doctor) {
		this.doctor = doctor;
	}

	public Integer getSecretary() {
		return secretary;
	}

	public void setSecretary(Integer secretary) {
		this.secretary = secretary;
	}

	public Long getPatient() {
		return patient;
	}

	public void setPatient(Long patient) {
		this.patient = patient;
	}
}
