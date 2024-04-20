package com.medteamb.medteamb.service.dto.patient;

import com.medteamb.medteamb.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientDTOMapper {

    public PatientResponseDTO mapToResponseDTO(Patient patient) {
        if (patient == null) {
            return null;
        }
        PatientResponseDTO dto = new PatientResponseDTO();
        dto.setPatientId(patient.getPatientID());
        dto.setPatientName(patient.getPatientName());
        dto.setPatientSurname(patient.getPatientSurname());
        dto.setTaxCode(patient.getTaxCode());
        dto.setPatientPhoneNumber(patient.getPatientPhoneNumber());
        dto.setPatientEmail(patient.getPatientEmail());
        return dto;
    }

    public Patient mapFromRequestDTO(PatientRequestDTO patientDTO) {
        if (patientDTO == null) {
            return null;
        }
        Patient patient = new Patient();
        patient.setPatientName(patientDTO.getPatientName());
        patient.setPatientSurname(patientDTO.getPatientSurname());
        patient.setTaxCode(patientDTO.getTaxCode());
        patient.setPatientPhoneNumber(patientDTO.getPatientPhoneNumber());
        patient.setPatientEmail(patientDTO.getPatientEmail());
        return patient;
    }
}
