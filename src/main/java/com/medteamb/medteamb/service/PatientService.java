package com.medteamb.medteamb.service;

import com.medteamb.medteamb.model.Patient;
import com.medteamb.medteamb.repository.PatientRepository;
import com.medteamb.medteamb.service.dto.patient.PatientDTOMapper;
import com.medteamb.medteamb.service.dto.patient.PatientRequestDTO;
import com.medteamb.medteamb.service.dto.patient.PatientResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientDTOMapper patientDTOMapper;

    public PatientService(PatientRepository patientRepository, PatientDTOMapper patientDTOMapper) {
        this.patientRepository = patientRepository;
        this.patientDTOMapper = patientDTOMapper;
    }

    public List<PatientResponseDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(patientDTOMapper::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public PatientResponseDTO getPatientById(Integer patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        return patientDTOMapper.mapToResponseDTO(patient);
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientDTO) {
        Patient patient = patientDTOMapper.mapFromRequestDTO(patientDTO);
        Patient savedPatient = patientRepository.save(patient);
        return patientDTOMapper.mapToResponseDTO(savedPatient);
    }

    public PatientResponseDTO updatePatient(Integer patientId, PatientRequestDTO patientDTO) {
        Patient existingPatient = patientRepository.findById(patientId).orElseThrow();

        existingPatient.setPatientName(patientDTO.getPatientName());
        existingPatient.setPatientSurname(patientDTO.getPatientSurname());
        existingPatient.setPatientPhoneNumber(patientDTO.getPatientPhoneNumber());
        existingPatient.setPatientEmail(patientDTO.getPatientEmail());

        Patient updatedPatient = patientRepository.save(existingPatient);
        return patientDTOMapper.mapToResponseDTO(updatedPatient);
    }

    public void deletePatient(Integer patientId) {
        patientRepository.deleteById(patientId);
    }


}
