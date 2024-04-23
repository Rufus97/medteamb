package com.medteamb.medteamb.service.dto.patient;

import com.medteamb.medteamb.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class DTOmapper {


    public  PatientDTO mapFromPatientToResponse(Patient patient){
        PatientDTO responseDTO = new PatientDTO();

        responseDTO.setPatientName(patient.getPatientName());
        responseDTO.setPatientSurname(patient.getPatientSurname());
        responseDTO.setTaxCode(patient.getTaxCode());

        return responseDTO;
    }
    
    public Patient requestToPatientMapping(PatientRequestBodyDTO patientRequestBodyDTO) {
    	Patient patient = new Patient();
    	patient.setPatientEmail(patientRequestBodyDTO.getPatientEmail());
    	patient.setPatientName(patientRequestBodyDTO.getPatientName());
    	patient.setPatientPhoneNumber(patientRequestBodyDTO.getPatientPhoneNumber());
    	patient.setPatientSurname(patientRequestBodyDTO.getPatientSurname());
    	patient.setTaxCode(patientRequestBodyDTO.getTaxCode());
    	patient.setUser(patientRequestBodyDTO.getUser());
    	return patient;
    }
}
