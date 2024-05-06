package com.medteamb.medteamb.model.patient;

import com.medteamb.medteamb.model.User;
import jakarta.persistence.*;


@Entity
@Table(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long patientID;
    @Column(nullable = false)
	private String patientName;
	@Column(nullable = false)
	private String patientSurname;
	@Column(nullable = false, unique = true)
	private String taxCode;
	@Column(nullable = false, unique = true)
	private String patientPhoneNumber;
	@Column(unique = true)
	private String patientEmail;
    public Patient(){};
	public Patient(String patientName, String patientSurname, String taxCode, String patientPhoneNumber, String patientEmail) {
		this.patientName = patientName;
		this.patientSurname = patientSurname;
		this.patientPhoneNumber = patientPhoneNumber;
		this.taxCode = taxCode;
		this.patientEmail = patientEmail;
	}

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Long getPatientID() {
		return patientID;
	}

	public void setPatientID(Long patientID) {
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

