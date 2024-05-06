package com.medteamb.medteamb.model;

import java.time.LocalTime;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doctor_id")
	private Long doctorID;
	
	@Column(name = "doctor_name", nullable = false)
	private String doctorName;
	
	@Column(name = "doctor_surname", nullable = false)
	private String doctorSurname;
	
	@Column(name = "doctor_phone_number", nullable = false)
	private String doctorPhoneNumber;
	
	@Column(name = "doctor_email", unique = true)
	private String doctorEmail;
	
	@Enumerated(EnumType.STRING)
	private Specialization specialization;
	
	@Column(name = "beginning_work_time", nullable = false)
	private LocalTime beginningWorkTime;
	
	@Column(name = "appointments_duration", nullable = false)
	private Integer appointmentsDuration;
	
	@Column(name = "appointments_per_day", nullable = false)
	private Integer appointmentsPerDay;
	
	@Column(name = "agenda_months_range", nullable = false)
	private Integer agendaMonthsRange;

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
	
	public LocalTime getBeginningWorkTime() {
		return beginningWorkTime;
	}
	
	public void setBeginningWorkTime(LocalTime beginningWorkTime) {
		this.beginningWorkTime = beginningWorkTime;
	}
	
	public Integer getAppointmentsDuration() {
		return appointmentsDuration;
	}

	public void setAppointmentsDuration(Integer appointmentsDuration) {
		this.appointmentsDuration = appointmentsDuration;
	}

	public Integer getAppointmentsPerDay() {
		return appointmentsPerDay;
	}

	public void setAppointmentsPerDay(Integer appointmentsPerDay) {
		this.appointmentsPerDay = appointmentsPerDay;
	}

	public Integer getAgendaMonthsRange() {
		return agendaMonthsRange;
	}

	public void setAgendaMonthsRange(Integer agendaMonthsRange) {
		this.agendaMonthsRange = agendaMonthsRange;
	}

	public void updateThisDocto(Doctor newDoc){
		this.doctorEmail = newDoc.getDoctorEmail();
		this.doctorName = newDoc.getDoctorName();
		this.doctorSurname = newDoc.getDoctorSurname();
		this.doctorPhoneNumber = newDoc.getDoctorPhoneNumber();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(appointmentsDuration, appointmentsPerDay, agendaMonthsRange, beginningWorkTime, doctorEmail,
				doctorID, doctorName, doctorPhoneNumber, doctorSurname, specialization, user);
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
		return Objects.equals(appointmentsDuration, other.appointmentsDuration)
				&& Objects.equals(appointmentsPerDay, other.appointmentsPerDay)
				&& Objects.equals(agendaMonthsRange, other.agendaMonthsRange)
				&& Objects.equals(beginningWorkTime, other.beginningWorkTime)
				&& Objects.equals(doctorEmail, other.doctorEmail) && Objects.equals(doctorID, other.doctorID)
				&& Objects.equals(doctorName, other.doctorName)
				&& Objects.equals(doctorPhoneNumber, other.doctorPhoneNumber)
				&& Objects.equals(doctorSurname, other.doctorSurname) && specialization == other.specialization
				&& Objects.equals(user, other.user);
	}
}
