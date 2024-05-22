package com.medteamb.medteamb.service.dto.patient;

public class PatientUpdateAppointment {
	

	private Integer newAppointmentID;
	private Integer oldAppointmentID;
	public Integer getNewAppointmentID() {
		return newAppointmentID;
	}
	public void setNewAppointmentID(Integer newAppointmentID) {
		this.newAppointmentID = newAppointmentID;
	}
	public Integer getOldAppointmentID() {
		return oldAppointmentID;
	}
	public void setOldAppointmentID(Integer oldAppointmentID) {
		this.oldAppointmentID = oldAppointmentID;
	}
	
}
