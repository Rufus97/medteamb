package com.medteamb.medteamb.model.agenda;

import java.time.LocalDateTime;
import java.util.Objects;

import com.medteamb.medteamb.model.Doctor;
import com.medteamb.medteamb.model.Patient;
import com.medteamb.medteamb.model.Secretary;

import jakarta.persistence.*;

@Entity
@Table(name = "appointment")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointment_id")
	private Integer appointmentID;
	
	@Column(nullable = false)
	private LocalDateTime appointmentDateTime;
	
	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;
	
	private String medicalService;
	
	private String location;
	
	//database relations from here on...
	
	private String taxCode;
	
	@ManyToOne()
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;
	
	@ManyToOne()
	@JoinColumn(name = "secretary_id")
	private Secretary secretary;
	
	@ManyToOne()
	@JoinColumn(name = "patient_id")
	private Patient patient;

	//his constructors...
	
	public Appointment() {}
	
	public Appointment(LocalDateTime appointmentDateTime, Doctor doctor) {
		this.appointmentDateTime = appointmentDateTime;
		this.doctor = doctor;
		this.status = AppointmentStatus.EMPTY;
	}
	
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
