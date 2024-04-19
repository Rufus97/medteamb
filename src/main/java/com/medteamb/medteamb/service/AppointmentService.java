package com.medteamb.medteamb.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.medteamb.medteamb.model.Appointment;
import com.medteamb.medteamb.repository.AppointmentRepository;
import com.medteamb.medteamb.repository.DoctorRepository;
import com.medteamb.medteamb.repository.PatientRepository;
import com.medteamb.medteamb.repository.SecretaryRepository;
import com.medteamb.medteamb.service.dto.appointment.AppointmentIterableDTO;
import com.medteamb.medteamb.service.dto.appointment.AppointmentRequestDTO;
import com.medteamb.medteamb.service.dto.appointment.AppointmentResponseDTO;
import com.medteamb.medteamb.service.dto.appointment.DTOAppointmentMapper;

@Service
public class AppointmentService {
	
	private DTOAppointmentMapper dtoMapper;
	private AppointmentRepository appointmentRepository;
	private DoctorRepository doctorRepository;
	private PatientRepository patientRepository;
	private SecretaryRepository secretaryRepository;
	
	public AppointmentService() {}
	
	public AppointmentService(DTOAppointmentMapper dtoMapper, AppointmentRepository appointmentRepository,
			DoctorRepository doctorRepository, PatientRepository patientRepository,
			SecretaryRepository secretaryRepository) {
		this.dtoMapper = dtoMapper;
		this.appointmentRepository = appointmentRepository;
		this.doctorRepository = doctorRepository;
		this.patientRepository = patientRepository;
		this.secretaryRepository = secretaryRepository;
	}

	public AppointmentResponseDTO createAppointment(AppointmentRequestDTO appointmentRequest) {
		Appointment appointment = dtoMapper.requestToAppointmentMapping(appointmentRequest);
		appointment.setDoctor(doctorRepository.findById(appointmentRequest.getDoctor()).get());
		appointment.setSecretary(secretaryRepository.findById(appointmentRequest.getSecretary()).get());
		appointment.setPatient(patientRepository.findById(appointmentRequest.getPatient()).get());
		appointment.setTaxCode(patientRepository.findById(appointmentRequest.getPatient()).get().getTaxCode());
		Appointment newAppointment = appointmentRepository.save(appointment);
		return dtoMapper.appointmentToResponse(newAppointment);
	}
	
	public AppointmentIterableDTO getAllAppointments() {
		return new AppointmentIterableDTO(appointmentRepository.findAll());
	}
	
	public AppointmentResponseDTO getAppointmentByID(Integer appointmentID) {
		Appointment appointment = appointmentRepository.findById(appointmentID).get();
		return dtoMapper.appointmentToResponse(appointment);
	}
	
	public AppointmentResponseDTO updateAppointment(Integer appointmentID, AppointmentRequestDTO appointmentRequest) {
		if((appointmentRepository.findById(appointmentID)).isPresent()) {
			Appointment appointment = appointmentRepository.findById(appointmentID).get();
			appointment.setAppointmentDateTime(appointmentRequest.getAppointmentDateTime());
			appointment.setLocation(appointmentRequest.getLocation());
			appointment.setMedicalService(appointmentRequest.getMedicalService());
			appointment.setStatus(appointmentRequest.getStatus());
			appointment.setDoctor(doctorRepository.findById(appointmentRequest.getDoctor()).get());
			appointment.setSecretary(secretaryRepository.findById(appointmentRequest.getSecretary()).get());
			appointment.setPatient(patientRepository.findById(appointmentRequest.getPatient()).get());
			appointment.setTaxCode(patientRepository.findById(appointmentRequest.getPatient()).get().getTaxCode());
			appointment = appointmentRepository.save(appointment);
			return dtoMapper.appointmentToResponse(appointment);
		}
		else return null;
	}
	
	public AppointmentResponseDTO deleteAppointment(Integer appointmentID) {
		Optional<Appointment> appointment = appointmentRepository.findById(appointmentID);
		if (appointment.isPresent()) {
			appointmentRepository.deleteById(appointment.get().getAppointmentID());
			return dtoMapper.appointmentToResponse(appointment.get());
		}
		else return null;
		
	}
	
}
