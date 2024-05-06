package com.medteamb.medteamb.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medteamb.medteamb.service.AppointmentService;
import com.medteamb.medteamb.service.dto.appointment.AppointmentListDTO;
import com.medteamb.medteamb.service.dto.appointment.AppointmentRequestDTO;
import com.medteamb.medteamb.service.dto.appointment.AppointmentResponseDTO;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

	private AppointmentService appointmentService;
	
	public AppointmentController(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	
	@GetMapping("/getAll")
	public AppointmentListDTO getAllAppointments() {
		return appointmentService.getAllAppointments();
	}
	
	@GetMapping("/get/{appointmentID}")
	public AppointmentResponseDTO getAppointmenByID(@PathVariable Integer appointmentID) {
		return appointmentService.getAppointmentByID(appointmentID);
	}
	
	@PutMapping("/update/{appointmentID}")
	public AppointmentResponseDTO updateAppointment(@PathVariable Integer appointmentID, @RequestBody AppointmentRequestDTO appointmentRequestDTO) {
		return appointmentService.updateAppointment(appointmentID, appointmentRequestDTO);
	}
	
	@DeleteMapping("/delete/{appointmentID}")
	public AppointmentResponseDTO deleteAppointmentByID(@PathVariable Integer appointmentID) {
		return appointmentService.deleteAppointment(appointmentID);
	}
	
}
