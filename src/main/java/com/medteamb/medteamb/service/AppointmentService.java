package com.medteamb.medteamb.service;

import org.springframework.stereotype.Service;

import com.medteamb.medteamb.model.Appointment;
import com.medteamb.medteamb.repository.AppointmentRepository;
import com.medteamb.medteamb.repository.DoctorRepository;
import com.medteamb.medteamb.repository.PatientRepository;
import com.medteamb.medteamb.repository.SecretaryRepository;
import com.medteamb.medteamb.service.dto.DTOMapper;
import com.medteamb.medteamb.service.dto.appointment.AppointmentRequestDTO;
import com.medteamb.medteamb.service.dto.appointment.AppointmentResponseDTO;

@Service
public class AppointmentService {
	
	private DTOMapper dtoMapper;
	private AppointmentRepository appointmentRepository;
	private DoctorRepository doctorRepository;
	private PatientRepository patientRepository;
	private SecretaryRepository secretaryRepository;
	
	public AppointmentService(DTOMapper dtoMapper, AppointmentRepository appointmentRepository,
			DoctorRepository doctorRepository, PatientRepository patientRepository,
			SecretaryRepository secretaryRepository) {
		this.dtoMapper = dtoMapper;
		this.appointmentRepository = appointmentRepository;
		this.doctorRepository = doctorRepository;
		this.patientRepository = patientRepository;
		this.secretaryRepository = secretaryRepository;
	}

	public AppointmentResponseDTO createAppointment(AppointmentRequestDTO appointmentRequest) {
		Appointment appointmentSelection = dtoMapper.requestToAppointmentMapping(appointmentRequest);
		appointmentSelection.setDoctor(doctorRepository.findById(appointmentRequest.getDoctor()).get());
		appointmentSelection.setSecretary(secretaryRepository.findById(appointmentRequest.getSecretary()).get());
		appointmentSelection.setPatient(patientRepository.findById(appointmentRequest.getPatient()).get());
		appointmentSelection.setTaxCode(patientRepository.findById(appointmentRequest.getPatient()).get().getTaxCode());
		Appointment appointment = appointmentRepository.save(appointmentSelection);
		return dtoMapper.appointmentToResponse(appointment);
	}
	
}
