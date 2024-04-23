package com.medteamb.medteamb.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointment_id")
	private Integer appointmentID;
	
	@Column(nullable = false)
	private LocalDateTime appointmentDateTime;
	
	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;
	
	@Column(nullable = false)
	private String medicalService;
	
	@Column(nullable = false)
	private String location;
	
	//database relations from here on...
	
	//private String taxCode;
	
	@Column(name = "doctor_id")
	private Integer doctor;
	
	@Column(name = "secretary_id")
	private Integer secretary;

	@Column(name = "patient_id")
	private Integer patient;

	public Integer getAppointmentID() {
		return appointmentID;
	}
	
	public Integer getPatient() {
		return patient;
	}

	public void setPatient(Integer patient) {
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
	
//	public String getTaxCode() {
//		return taxCode;
//	}
//
//	public void setTaxCode(String taxCode) {
//		this.taxCode = taxCode;
//	}


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

	public void setDoctor(Integer doctor) {
		this.doctor = doctor;
	}

	public Integer getSecretary() {
		return secretary;
	}

	public void setSecretary(Integer secretary) {
		this.secretary = secretary;
	}

	@Override
	public int hashCode() {
		return Objects.hash(appointmentDateTime, appointmentID, doctor, location, medicalService, patient, secretary,
				status);
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
				&& status == other.status;
	}	
}
