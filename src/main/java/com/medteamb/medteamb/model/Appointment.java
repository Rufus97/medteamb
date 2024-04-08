package com.medteamb.medteamb.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "appointment")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer appointmentID;
	@Column(unique = true, nullable = false)
	private LocalDateTime appointmentDateTime;
	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;
	private String medicalProvision; //prestazione medica
	private String place;

	//da qui si aggiungono le relazioni per adesso
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

	private String taxCode; //codice fiscale

	public void setAppointmentID(Integer appointmentID) {
		this.appointmentID = appointmentID;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
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

	public String getMedicalProvision() {
		return medicalProvision;
	}

	public void setMedicalProvision(String medicalProvision) {
		this.medicalProvision = medicalProvision;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public Integer getAppointmentID() {
		return appointmentID;
	}

	
}
