package com.medteamb.medteamb.service.dto.appointment;

import java.util.List;

import com.medteamb.medteamb.model.agenda.Appointment;

public class AppointmentListDTO {
	
	private List<Appointment> appointments;
	
	public AppointmentListDTO(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	
}
