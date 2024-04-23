package com.medteamb.medteamb.service.dto.appointment;

import org.springframework.stereotype.Component;

import com.medteamb.medteamb.model.Appointment;

@Component
public class DTOAppointmentMapper {
	
	public Appointment requestToAppointmentMapping(AppointmentRequestDTO appointmentRequest) {
		Appointment appointment = new Appointment();
		appointment.setDoctor(appointmentRequest.getDoctor());
		appointment.setPatient(appointmentRequest.getPatient());
		appointment.setSecretary(appointmentRequest.getSecretary());
		appointment.setAppointmentDateTime(appointmentRequest.getAppointmentDateTime());
		appointment.setLocation(appointmentRequest.getLocation());
		appointment.setMedicalService(appointmentRequest.getMedicalService());
		appointment.setStatus(appointmentRequest.getStatus());
		return appointment;
	}
	
	public AppointmentResponseDTO appointmentToResponse(Appointment appointment) {
		AppointmentResponseDTO appointmentResponse = new AppointmentResponseDTO();
		appointmentResponse.setAppointmentID(appointment.getAppointmentID());
		appointmentResponse.setAppointmentDateTime(appointment.getAppointmentDateTime());
		appointmentResponse.setDoctor(appointment.getDoctor());
		appointmentResponse.setLocation(appointment.getLocation());
		appointmentResponse.setMedicalService(appointment.getMedicalService());
		appointmentResponse.setPatient(appointment.getPatient());
		appointmentResponse.setSecretary(appointment.getSecretary());
		appointmentResponse.setStatus(appointment.getStatus());
		return appointmentResponse;
	}
	
}
