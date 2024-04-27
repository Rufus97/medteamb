package com.medteamb.medteamb.service.dto.appointment;

import com.medteamb.medteamb.model.Appointment;

public class AppointmentIterableDTO {
	
	private Iterable<Appointment> appointments;
	
	public AppointmentIterableDTO(Iterable<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Iterable<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Iterable<Appointment> appointments) {
		this.appointments = appointments;
	}
	
}
