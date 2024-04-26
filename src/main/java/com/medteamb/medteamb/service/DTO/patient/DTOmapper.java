package com.medteamb.medteamb.service.dto.patient;

import com.medteamb.medteamb.model.Patient.Patient;
import com.medteamb.medteamb.model.Patient.SpecialAppointments;
import com.medteamb.medteamb.service.dto.patient.SpecialAppointments.SpecialResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class DTOmapper{


    public PatientResponseDTO mapFromPatientToResponse(Patient response){
        PatientResponseDTO responseDTO = new PatientResponseDTO();

        responseDTO.setPatientName(response.getPatientName());
        responseDTO.setPatientSurname(response.getPatientSurname());
        responseDTO.setTaxCode(response.getTaxCode());

        return responseDTO;
    }

    public Patient mapFromRequestToPatient(PatientRequestDTO request){
       return new Patient(request.getPatientName(),
               request.getPatientSurname(),
               request.getPatientPhoneNumber(),
               request.getTaxCode());
    }
    public SpecialResponseDTO mapFromSpecialRequest(SpecialAppointments response){
        SpecialResponseDTO responseDTO = new SpecialResponseDTO();
        responseDTO.setId(response.getPatient().getPatientID());
        responseDTO.setAppointmentDate(response.getAppointmentDate());
        responseDTO.setAppointmentHour(response.getAppointmentHour());
        responseDTO.setId(response.getId());
        return responseDTO;
    }

}
