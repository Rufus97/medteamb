package com.medteamb.medteamb.model;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer patientID;
	private String patientName;
	private String patientSurname;
	private String taxCode;
	private String patientPhoneNumber;
	private String patientEmail;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="appointmentID", orphanRemoval = true)
	private Set<Appointment> appointments;

	public Integer getPatientID() {
		return patientID;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientSurname() {
		return patientSurname;
	}

	public void setPatientSurname(String patientSurname) {
		this.patientSurname = patientSurname;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getPatientPhoneNumber() {
		return patientPhoneNumber;
	}

	public void setPatientPhoneNumber(String patientPhoneNumber) {
		this.patientPhoneNumber = patientPhoneNumber;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	@Override
	public int hashCode() {
		return Objects.hash(patientEmail, patientID, patientName, patientPhoneNumber, patientSurname, taxCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		return Objects.equals(patientEmail, other.patientEmail) && Objects.equals(patientID, other.patientID)
				&& Objects.equals(patientName, other.patientName)
				&& Objects.equals(patientPhoneNumber, other.patientPhoneNumber)
				&& Objects.equals(patientSurname, other.patientSurname) && Objects.equals(taxCode, other.taxCode);
	}
}
