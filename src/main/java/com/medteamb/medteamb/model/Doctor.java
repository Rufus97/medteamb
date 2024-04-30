package com.medteamb.medteamb.model;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doctor_id")
	private Long doctorID;
	
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

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public void setDoctorID(Long doctorID) {
		this.doctorID = doctorID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getDoctorID() {
		return doctorID;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDoctorSurname() {
		return doctorSurname;
	}
	public void setDoctorSurname(String doctorSurname) {
		this.doctorSurname = doctorSurname;
	}
	public String getDoctorPhoneNumber() {
		return doctorPhoneNumber;
	}
	public void setDoctorPhoneNumber(String doctorPhoneNumber) {
		this.doctorPhoneNumber = doctorPhoneNumber;
	}
	public String getDoctorEmail() {
		return doctorEmail;
	}
	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
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

	public void updateThisDocto(Doctor newDoc){
		this.doctorEmail = newDoc.getDoctorEmail();
		this.doctorName = newDoc.getDoctorName();
		this.doctorSurname = newDoc.getDoctorSurname();
		this.doctorPhoneNumber = newDoc.getDoctorPhoneNumber();
	}
}
