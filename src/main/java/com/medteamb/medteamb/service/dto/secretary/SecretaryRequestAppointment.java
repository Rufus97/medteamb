package com.medteamb.medteamb.service.dto.secretary;

public class SecretaryRequestAppointment {
	
	private Long secretaryID;
	private Integer appointmentID;
	public Long getSecretaryID() {
		return secretaryID;
	}
	public void setSecretaryID(Long secretaryID) {
		this.secretaryID = secretaryID;
	}
	public Integer getAppointmentID() {
		return appointmentID;
	}
	public void setAppointmentID(Integer appointmentID) {
		this.appointmentID = appointmentID;
	}
	
}
