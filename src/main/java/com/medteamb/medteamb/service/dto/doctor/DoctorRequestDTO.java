package com.medteamb.medteamb.service.dto.doctor;

import java.time.LocalTime;

import com.medteamb.medteamb.model.Specialization;

public class DoctorRequestDTO {
	
	private Long doctorID;
	private String doctorName;
	private String doctorSurname;
	private String doctorPhoneNumber;
	private String doctorEmail;
	private Specialization specialization;
	private LocalTime beginningWorkTime;
	private Integer appointmentsDuration;
	private Integer appointmentsPerDay;
	private Integer agendaMonthsRange;

	private DoctorRequestDTO(Builder builder) {
		this.doctorID = builder.doctorID;
		this.doctorName = builder.doctorName;
		this.doctorSurname = builder.doctorSurname;
		this.doctorPhoneNumber = builder.doctorPhoneNumber;
		this.doctorEmail = builder.doctorEmail;
		this.specialization = builder.specialization;
		this.beginningWorkTime = builder.beginningWorkTime;
		this.appointmentsDuration = builder.appointmentsDuration;
		this.appointmentsPerDay = builder.appointmentsPerDay;
		this.agendaMonthsRange = builder.agendaMonthsRange;
	}

	public Long getDoctorID() {


	public DoctorRequestDTO() { }
	
	public Integer getDoctorID() {

		return doctorID;
	}
	public void setDoctorID(Long doctorID) {
		this.doctorID = doctorID;
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

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private Long doctorID;
		private String doctorName;
		private String doctorSurname;
		private String doctorPhoneNumber;
		private String doctorEmail;
		private Specialization specialization;
		private LocalTime beginningWorkTime;
		private Integer appointmentsDuration;
		private Integer appointmentsPerDay;
		private Integer agendaMonthsRange;

		private Builder() {
		}

		public Builder withDoctorID(Long doctorID) {
			this.doctorID = doctorID;
			return this;
		}

		public Builder withDoctorName(String doctorName) {
			this.doctorName = doctorName;
			return this;
		}

		public Builder withDoctorSurname(String doctorSurname) {
			this.doctorSurname = doctorSurname;
			return this;
		}

		public Builder withDoctorPhoneNumber(String doctorPhoneNumber) {
			this.doctorPhoneNumber = doctorPhoneNumber;
			return this;
		}

		public Builder withDoctorEmail(String doctorEmail) {
			this.doctorEmail = doctorEmail;
			return this;
		}

		public Builder withSpecialization(Specialization specialization) {
			this.specialization = specialization;
			return this;
		}

		public Builder withBeginningWorkTime(LocalTime beginningWorkTime) {
			this.beginningWorkTime = beginningWorkTime;
			return this;
		}

		public Builder withAppointmentsDuration(Integer appointmentsDuration) {
			this.appointmentsDuration = appointmentsDuration;
			return this;
		}

		public Builder withAppointmentsPerDay(Integer appointmentsPerDay) {
			this.appointmentsPerDay = appointmentsPerDay;
			return this;
		}

		public Builder withAgendaMonthsRange(Integer agendaMonthsRange) {
			this.agendaMonthsRange = agendaMonthsRange;
			return this;
		}

		public DoctorRequestDTO build() {
			return new DoctorRequestDTO(this);
		}
	}
	
}
