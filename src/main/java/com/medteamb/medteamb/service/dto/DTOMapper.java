package com.medteamb.medteamb.service.dto;

import com.medteamb.medteamb.model.Appointment;
import com.medteamb.medteamb.service.dto.appointment.AppointmentRequestDTO;
import com.medteamb.medteamb.service.dto.appointment.AppointmentResponseDTO;

public class DTOMapper {
	
	public Appointment requestToAppointmentMapping(AppointmentRequestDTO appointmentRequest) {
		Appointment appointment = new Appointment();
		appointment.setAppointmentDateTime(appointmentRequest.getAppointmentDateTime());
		appointment.setDoctor(appointmentRequest.getDoctor());
		appointment.setLocation(appointmentRequest.getLocation());
		appointment.setMedicalService(appointmentRequest.getMedicalService());
		appointment.setPatient(appointmentRequest.getPatient());
		appointment.setSecretary(appointmentRequest.getSecretary());
		appointment.setStatus(appointmentRequest.getStatus());
		appointment.setTaxCode();
		return appointment;
	}
	
	public AppointmentResponseDTO appointmentToResponse(Appointment appointment) {
		AppointmentResponseDTO appointmentResponse = new AppointmentResponseDTO();
		appointmentResponse.setAppointmentDateTime(appointment.getAppointmentDateTime());
		appointmentResponse.setDoctor(appointment.getDoctor());
		appointmentResponse.setLocation(appointment.getLocation());
		appointmentResponse.setMedicalService(appointment.getMedicalService());
		appointmentResponse.setPatient(appointment.getPatient());
		appointmentResponse.setSecretary(appointment.getSecretary());
		appointmentResponse.setStatus(appointment.getStatus());
		appointmentResponse.setTaxCode(appointment.getTaxCode());
		return appointmentResponse;
	}
	
}
