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
	
	@Column
	private String medicalProvision; //prestazione medica
	
	@Column
	private String place;

	@JoinColumn(name="medic id")
	@ManyToOne
	private Medic medic;
	
	@JoinColumn(name="secretary id")
	@ManyToOne
	private Secretary secretary;

	@JoinColumn(name="patient id")
	@ManyToOne
	private Patient patient;
	


	private String taxCode; //codice fiscale

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

	public Medic getMedic() {
		return medic;
	}

	public void setMedic(Medic medic) {
		this.medic = medic;
	}

	public Secretary getSecretary() {
		return secretary;
	}

	public void setSecretary(Secretary secretary) {
		this.secretary = secretary;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
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

	@Override
	public int hashCode() {
		return Objects.hash(appointmentDateTime, appointmentID, medic, medicalProvision, patient, place, secretary,
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
				&& Objects.equals(appointmentID, other.appointmentID) && Objects.equals(medic, other.medic)
				&& Objects.equals(medicalProvision, other.medicalProvision) && Objects.equals(patient, other.patient)
				&& Objects.equals(place, other.place) && Objects.equals(secretary, other.secretary)
				&& status == other.status && Objects.equals(taxCode, other.taxCode);
	}
	
}
