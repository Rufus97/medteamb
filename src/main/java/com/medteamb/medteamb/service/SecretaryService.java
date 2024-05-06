package com.medteamb.medteamb.service;

import com.medteamb.medteamb.model.Secretary;
import com.medteamb.medteamb.model.agenda.Appointment;
import com.medteamb.medteamb.model.agenda.AppointmentStatus;
import com.medteamb.medteamb.model.patient.Patient;
import com.medteamb.medteamb.repository.AppointmentRepository;
import com.medteamb.medteamb.repository.SecretaryRepository;
import com.medteamb.medteamb.repository.patient.PatientRepository;
import com.medteamb.medteamb.service.dto.appointment.AppointmentResponseDTO;
import com.medteamb.medteamb.service.dto.appointment.DTOAppointmentMapper;
import com.medteamb.medteamb.service.dto.secretary.SecretaryDTOMapper;
import com.medteamb.medteamb.service.dto.secretary.SecretaryRequestAppointment;
import com.medteamb.medteamb.service.dto.secretary.SecretaryRequestDTO;
import com.medteamb.medteamb.service.dto.secretary.SecretaryResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SecretaryService {

	private final DTOAppointmentMapper appointmentMapper;
	private final PatientRepository patientRepository;
	private final AppointmentRepository appointmentRepository;
    private final SecretaryRepository secretaryRepository;
    private final SecretaryDTOMapper secretaryDTOMapper;

    public SecretaryService(AppointmentRepository appointmentRepository, SecretaryRepository secretaryRepository, 
    							SecretaryDTOMapper secretaryDTOMapper, PatientRepository patientRepository,
    							DTOAppointmentMapper appointmentMapper) {
        this.secretaryRepository = secretaryRepository;
        this.secretaryDTOMapper = secretaryDTOMapper;
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.appointmentMapper = appointmentMapper;
    }
    
    public List<SecretaryResponseDTO> getAllSecretaries() {
        List<Secretary> secretaries = secretaryRepository.findAll();
        return secretaries.stream()
                .map(secretaryDTOMapper::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public SecretaryResponseDTO getSecretaryById(Long id) {
        Secretary secretary = secretaryRepository.findById(id).orElseThrow();
        return secretaryDTOMapper.mapToResponseDTO(secretary);
    }

    public SecretaryResponseDTO createSecretary(SecretaryRequestDTO secretaryDTO) {
        Secretary secretary = secretaryDTOMapper.mapFromRequestDTO(secretaryDTO);
        Secretary savedSecretary = secretaryRepository.save(secretary);
        return secretaryDTOMapper.mapToResponseDTO(savedSecretary);
    }

    public SecretaryResponseDTO updateSecretary(Long id, SecretaryRequestDTO secretaryDTO) {
        Secretary existingSecretary = secretaryRepository.findById(id).orElseThrow();
        existingSecretary.setSecretaryName(secretaryDTO.getSecretaryName());
        existingSecretary.setSecretarySurname(secretaryDTO.getSecretarySurname());
        existingSecretary.setSecretaryPhoneNumber(secretaryDTO.getSecretaryPhoneNumber());
        existingSecretary.setSecretaryEmail(secretaryDTO.getSecretaryEmail());
        Secretary updatedSecretary = secretaryRepository.save(existingSecretary);
        return secretaryDTOMapper.mapToResponseDTO(updatedSecretary);
    }

    public void deleteSecretary(Long id) {
        secretaryRepository.deleteById(id);
    }

    public AppointmentResponseDTO newAppointmentRequest(SecretaryRequestAppointment secretaryRequestAppointment, String taxCode) {
    	Appointment appointment = appointmentRepository.findById(secretaryRequestAppointment.getAppointmentID()).get();
    	Patient patient = patientRepository.findBytaxCode(taxCode).get();
    	appointment.setLocation("Develhope");
    	appointment.setMedicalService("Visita");
    	appointment.setPatient(patient);
    	appointment.setSecretary(secretaryRepository.findById(secretaryRequestAppointment.getSecretaryID()).get());
    	appointment.setStatus(AppointmentStatus.TO_DO);
    	appointment.setTaxCode(patient.getTaxCode());
    	appointment = appointmentRepository.save(appointment);
    	return appointmentMapper.appointmentToResponse(appointment);
    }

}