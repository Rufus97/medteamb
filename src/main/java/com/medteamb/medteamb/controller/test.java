package com.medteamb.medteamb.controller;

import org.springframework.web.bind.annotation.RestController;

import com.medteamb.medteamb.service.AppointmentService;
import com.medteamb.medteamb.service.dto.appointment.AppointmentRequestDTO;
import com.medteamb.medteamb.service.dto.appointment.AppointmentResponseDTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class test {
	
	private AppointmentService appointmentService;
	
	public test(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	
	@PostMapping("/postAppointment")
	public AppointmentResponseDTO createAppointment(@RequestBody AppointmentRequestDTO appointmentRequest) {
		return appointmentService.createAppointment(appointmentRequest);
	}
	
	
}
