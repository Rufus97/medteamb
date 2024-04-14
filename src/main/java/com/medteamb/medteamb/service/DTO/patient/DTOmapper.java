package com.medteamb.medteamb.service.dto.patient;

import com.medteamb.medteamb.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class DTOmapper {


    public  PatientDTO mapFromPatientToResponse(Patient response){
        PatientDTO responseDTO = new PatientDTO();

        responseDTO.setPatientName(response.getPatientName());
        responseDTO.setPatientSurname(response.getPatientSurname());
        responseDTO.setTaxCode(response.getTaxCode());

        return responseDTO;
    }
}
