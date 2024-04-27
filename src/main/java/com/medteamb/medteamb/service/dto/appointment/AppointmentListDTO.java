package com.medteamb.medteamb.service.dto.appointment;

import com.medteamb.medteamb.model.agenda.Appointment;

public class AppointmentListDTO {
	
	private Iterable<Appointment> appointments;
	
	public AppointmentListDTO(Iterable<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Iterable<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Iterable<Appointment> appointments) {
		this.appointments = appointments;
	}
	
}
