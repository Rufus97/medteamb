package com.medteamb.medteamb.service.dto.doctor;

import com.medteamb.medteamb.model.Specialization;

public class DoctorRequestDTO {
	
	private Integer doctorID;
	private String doctorName;
	private String doctorSurname;
	private String doctorPhoneNumber;
	private String doctorEmail;
	private Specialization specialization;

	private DoctorRequestDTO(Builder builder) {
		this.doctorID= builder.doctorID;
		this.doctorName = builder.doctorName;
		this.doctorSurname = builder.doctorSurname;
		this.doctorPhoneNumber = builder.doctorPhoneNumber;
		this.doctorEmail = builder.doctorEmail;
		this.specialization = builder.specialization;
	}

	public DoctorRequestDTO() { }
	

	public Integer getDoctorID() {
		return doctorID;
	}
	public void setDoctorID(Integer doctorID) {
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

	
	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private Integer doctorID;
		private String doctorName;
		private String doctorSurname;
		private String doctorPhoneNumber;
		private String doctorEmail;
		private Specialization specialization;

		private Builder() {
		}

		public Builder withDoctorID(Integer doctorID) {
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


		public DoctorRequestDTO build() {
			return new DoctorRequestDTO(this);
		}
	}
	
}
