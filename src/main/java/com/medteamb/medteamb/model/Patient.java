package com.medteamb.medteamb.model;

import jakarta.persistence.*;

import java.util.Objects;

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

//	@OneToOne
//	@JoinColumn(name = "user_id")
//	private User user;

	public Integer getPatientID() {
		return patientID;
	}

	public void setPatientID(Integer patientID) {
		this.patientID = patientID;
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Patient patient)) return false;
		return Objects.equals(getPatientID(), patient.getPatientID()) && Objects.equals(getPatientName(), patient.getPatientName()) && Objects.equals(getPatientSurname(), patient.getPatientSurname()) && Objects.equals(getTaxCode(), patient.getTaxCode()) && Objects.equals(getPatientPhoneNumber(), patient.getPatientPhoneNumber()) && Objects.equals(getPatientEmail(), patient.getPatientEmail());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getPatientID(), getPatientName(), getPatientSurname(), getTaxCode(), getPatientPhoneNumber(), getPatientEmail());
	}
}

