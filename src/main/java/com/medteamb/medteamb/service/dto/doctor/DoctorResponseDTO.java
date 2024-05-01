package com.medteamb.medteamb.service.dto.doctor;

import java.time.LocalTime;

import com.medteamb.medteamb.model.Specialization;
import com.medteamb.medteamb.service.ResponseHandler.BaseResponse;

public class DoctorResponseDTO extends BaseResponse {

	
	
	private String doctorName;
	private String doctorSurname;
	private String doctorPhoneNumber;
	private String doctorEmail;
	private Specialization specialization;
	private LocalTime beginningWorkTime;
	private Integer appointmentsDuration;
	private Integer appointmentsPerDay;
	private Integer agendaMonthsRange;
	
public DoctorResponseDTO() {}
	
	public DoctorResponseDTO(String message) {
		super(message);
	}

	public String getDoctorName() {
		return doctorName;
	}
	public String getDoctorSurname() {
		return doctorSurname;
	}
	public String getDoctorPhoneNumber() {
		return doctorPhoneNumber;
	}
	public String getDoctorEmail() {
		return doctorEmail;
	}
	public Specialization getSpecialization() {
		return specialization;
	}
	public LocalTime getBeginningWorkTime() {
		return beginningWorkTime;
	}
	public Integer getAppointmentsDuration() {
		return appointmentsDuration;
	}
	public Integer getAppointmentsPerDay() {
		return appointmentsPerDay;
	}
	public Integer getAgendaMonthsRange() {
		return agendaMonthsRange;
	}
	
	private DoctorResponseDTO(Builder builder) {
		
		this.doctorName= builder.doctorName;
		this.doctorSurname= builder.doctorSurname;
		this.doctorPhoneNumber= builder.doctorPhoneNumber;
		this.doctorEmail =builder.doctorEmail;
		this.specialization =builder.specialization;
		this.beginningWorkTime = builder.beginningWorkTime;
		this.appointmentsDuration = builder.appointmentsDuration;
		this.appointmentsPerDay = builder.appointmentsPerDay;
		this.agendaMonthsRange = builder.agendaMonthsRange;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder{
		
		private String doctorName;
		private String doctorSurname;
		private String doctorPhoneNumber;
		private String doctorEmail;
		private Specialization specialization;
		private LocalTime beginningWorkTime;
		private Integer appointmentsDuration;
		private Integer appointmentsPerDay;
		private Integer agendaMonthsRange;
		
		private Builder() {}
		
		
		
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
		
		public DoctorResponseDTO build() {
			return new DoctorResponseDTO(this);
		}
	}
}
