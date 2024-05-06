package com.medteamb.medteamb.service.dto.patient;

public class PatientRequestAppointment {

	private Long patientID;
	private Integer appointmentID;
	public Long getPatientID() {
		return patientID;
	}
	public void setPatientID(Long patientID) {
		this.patientID = patientID;
	}
	public Integer getAppointmentID() {
		return appointmentID;
	}
	public void setAppointmentID(Integer appointmentID) {
		this.appointmentID = appointmentID;
	}
	
}
