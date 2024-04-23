package com.medteamb.medteamb.service.dto.doctor;

import com.medteamb.medteamb.model.Specialization;
import com.medteamb.medteamb.model.User;


public class DoctorResponseDTO {

	private Integer doctorID;
	private String doctorName;
	private String doctorSurname;
	private String doctorPhoneNumber;
	private String doctorEmail;
	private Specialization specialization;
	private User user;

	public void setDoctorID(Integer doctorID) {
		this.doctorID = doctorID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getDoctorID() {
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
}
