package com.medteamb.medteamb.service.dto.appointment;

import java.time.LocalDateTime;

import com.medteamb.medteamb.model.Doctor;
import com.medteamb.medteamb.model.Secretary;
import com.medteamb.medteamb.model.agenda.AppointmentStatus;
import com.medteamb.medteamb.model.patient.Patient;

public class AppointmentResponseDTO {
	
	private Integer appointmentID;
	private LocalDateTime appointmentDateTime;
	private AppointmentStatus status;
	private String medicalService;
	private String location;
	private String taxCode;
	private Long doctor;

	
	public Integer getAppointmentID() {
		return appointmentID;
	}
	
	public void setAppointmentID(Integer appointmentID) {
		this.appointmentID = appointmentID;
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
	
	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
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

	public Long getDoctor() {
		return doctor;
	}

	public void setDoctor(Long doctor) {
		this.doctor = doctor;
	}


}
