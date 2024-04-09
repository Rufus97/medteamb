package com.medteamb.medteamb.model;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer doctorID;
	
	@Column(nullable = false)
	private String doctorName;
	
	@Column(nullable = false)
	private String doctorSurname;
	
	@Column(nullable = false)
	private String doctorPhoneNumber;
	
	@Column(unique = true)
	private String doctorEmail;
	
	@Enumerated(EnumType.STRING)
	private Specialization specialization;
	
	public Integer getDoctorID() {
		return doctorID;
	}
	public String getName() {
		return doctorName;
	}
	public void setName(String name) {
		this.doctorName = name;
	}
	public String getSurname() {
		return doctorSurname;
	}
	public void setSurname(String surname) {
		this.doctorSurname = surname;
	}
	public String getPhoneNumber() {
		return doctorPhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.doctorPhoneNumber = phoneNumber;
	}
	public String getEmail() {
		return doctorEmail;
	}
	public void setEmail(String email) {
		this.doctorEmail = email;
	}
	public Specialization getSpecialization() {
		return specialization;
	}
	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}
	@Override
	public int hashCode() {
		return Objects.hash(doctorID, doctorEmail, doctorName, doctorPhoneNumber, specialization, doctorSurname);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		return Objects.equals(doctorID, other.doctorID) && Objects.equals(doctorEmail, other.doctorEmail)
				&& Objects.equals(doctorName, other.doctorName) && Objects.equals(doctorPhoneNumber, other.doctorPhoneNumber)
				&& specialization == other.specialization && Objects.equals(doctorSurname, other.doctorSurname);
	}	
}
