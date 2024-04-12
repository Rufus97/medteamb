package com.medteamb.medteamb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer patientID;
    @Column(nullable = false)
	private String patientName;
	private String patientSurname;
	private String taxCode;
	private String patientPhoneNumber;
	private String patientEmail;
    public Patient(){};
	public Patient(String patientName, String patientSurname) {
		this.patientName = patientName;
		this.patientSurname = patientSurname;
	}

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Integer getPatientID() {
		return patientID;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void updateThisPatient(Patient newPatient){
		this.patientEmail = newPatient.getPatientEmail();
		this.patientName = newPatient.getPatientName();
		this.patientSurname = newPatient.getPatientSurname();
		this.patientPhoneNumber = newPatient.getPatientPhoneNumber();
		this.taxCode = newPatient.getTaxCode();
	}
}
