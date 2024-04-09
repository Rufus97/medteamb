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
	
	@Column(nullable = false)
	private String medicalService;
	
	@Column(nullable = false)
	private String location;
	
	//database relations from here on...
	
	@ManyToOne
	@JoinColumn(name = "taxCode" ,nullable = false)
	@Column(length = 16)
	private String taxCode;
	
	@ManyToOne
	@JoinColumn(name = "doctorID", nullable = false)
	private Doctor doctor;
	
	@ManyToOne
	@JoinColumn(name = "secretaryID", nullable = false)
	private Secretary secretary;
	
	@ManyToOne
	@JoinColumn(name = "patientID", nullable = false)
	private Patient patient;

	public Integer getAppointmentID() {
		return appointmentID;
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
		return medicalService;
	}

	public void setMedicalProvision(String medicalProvision) {
		this.medicalService = medicalProvision;
	}

	public String getPlace() {
		return location;
	}

	public void setPlace(String place) {
		this.location = place;
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

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Secretary getSecretary() {
		return secretary;
	}

	public void setSecretary(Secretary secretary) {
		this.secretary = secretary;
	}

	@Override
	public int hashCode() {
		return Objects.hash(appointmentDateTime, appointmentID, doctor, location, medicalService, patient, secretary,
				status, taxCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		return Objects.equals(appointmentDateTime, other.appointmentDateTime)
				&& Objects.equals(appointmentID, other.appointmentID) && Objects.equals(doctor, other.doctor)
				&& Objects.equals(location, other.location) && Objects.equals(medicalService, other.medicalService)
				&& Objects.equals(patient, other.patient) && Objects.equals(secretary, other.secretary)
				&& status == other.status && Objects.equals(taxCode, other.taxCode);
	}	
}
