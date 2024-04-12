package com.medteamb.medteamb.service;

import org.springframework.stereotype.Service;

import com.medteamb.medteamb.model.Appointment;
import com.medteamb.medteamb.repository.AppointmentRepository;
import com.medteamb.medteamb.service.dto.DTOMapper;
import com.medteamb.medteamb.service.dto.appointment.AppointmentRequestDTO;
import com.medteamb.medteamb.service.dto.appointment.AppointmentResponseDTO;

@Service
public class AppointmentService {
	
	private AppointmentRepository appointmentRepository;
	private DTOMapper dtoMapper = new DTOMapper();
	
	public AppointmentService(AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}
	
	public AppointmentResponseDTO createAppointment(AppointmentRequestDTO appointmentRequest) {
		Appointment appointment = appointmentRepository.save(dtoMapper.requestToAppointmentMapping(appointmentRequest));
		appointmentRepository.flush();
		return dtoMapper.appointmentToResponse(appointment);
	}
	
}
