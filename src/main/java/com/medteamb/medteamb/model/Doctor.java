package com.medteamb.medteamb.model;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table()
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
	
	@OneToMany(mappedBy="appointmentID")
	private Set<Appointment> appointments;
	
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
	public Set<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}
	@Override
	public int hashCode() {
		return Objects.hash(appointments, doctorEmail, doctorID, doctorName, doctorPhoneNumber, doctorSurname,
				specialization);
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
		return Objects.equals(appointments, other.appointments) && Objects.equals(doctorEmail, other.doctorEmail)
				&& Objects.equals(doctorID, other.doctorID) && Objects.equals(doctorName, other.doctorName)
				&& Objects.equals(doctorPhoneNumber, other.doctorPhoneNumber)
				&& Objects.equals(doctorSurname, other.doctorSurname) && specialization == other.specialization;
	}	
}
