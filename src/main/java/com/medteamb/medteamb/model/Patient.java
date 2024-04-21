package com.medteamb.medteamb.model;


import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient")
public class Patient {

	@Id
	@Column(name = "patient_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer patientID;
    @Column(nullable = false)
	private String patientName;
	@Column(nullable = false)
	private String patientSurname;
	@Column(nullable = false)
	private String taxCode;
	@Column(nullable = false)
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

	public void updateThisPatient(Patient newPatient){
		this.patientEmail = newPatient.getPatientEmail();
		this.patientName = newPatient.getPatientName();
		this.patientSurname = newPatient.getPatientSurname();
		this.patientPhoneNumber = newPatient.getPatientPhoneNumber();
		this.taxCode = newPatient.getTaxCode();
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

