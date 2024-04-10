package com.medteamb.medteamb.service.DTO.patientDTO;

import com.medteamb.medteamb.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class DTOmapper {


    public Patient mapFromRequestToPatient(PatientRequestBodyDTO requestDTO){
        Patient patient = new Patient();

        patient.setPatientName(requestDTO.getPatientName());
        patient.setPatientSurname(requestDTO.getPatientSurname());
        patient.setPatientPhoneNumber(requestDTO.getPatientPhoneNumber());
        patient.setTaxCode(requestDTO.getTaxCode());

        return patient;
    }

    public PatientResponseDTO mapFromPatientToResponse(Patient response){
        PatientResponseDTO responseDTO = new PatientResponseDTO();

        responseDTO.setPatientName(response.getPatientName());
        responseDTO.setPatientSurname(response.getPatientSurname());
        responseDTO.setTaxCode(response.getTaxCode());

        return responseDTO;
    }
}
