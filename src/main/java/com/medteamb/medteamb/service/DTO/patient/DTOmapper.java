package com.medteamb.medteamb.service.dto.patient;

import com.medteamb.medteamb.model.Calendar.AppointmentSlot;
import com.medteamb.medteamb.model.Patient.Patient;
import com.medteamb.medteamb.model.Patient.PatientRefert;
import com.medteamb.medteamb.model.Patient.Requests;
import com.medteamb.medteamb.model.Patient.SpecialAppointments;
import com.medteamb.medteamb.service.dto.patient.AppointmentSlots.AvaibleAppointmentResponseDTO;
import com.medteamb.medteamb.service.dto.patient.PatientAppointmentDTO.AppointmentResponseDTO;
import com.medteamb.medteamb.service.dto.patient.RefertDTO.RefertResponseDTO;
import com.medteamb.medteamb.service.dto.patient.SpecialAppointments.SpecialAppointmentResponseDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DTOmapper{


    public PatientResponseDTO mapFromPatientToResponse(Patient response){
        PatientResponseDTO responseDTO = new PatientResponseDTO();
        responseDTO.setId(response.getPatientID());
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
    public SpecialAppointmentResponseDTO mapFromSpecialRequest(SpecialAppointments response){
        SpecialAppointmentResponseDTO responseDTO = new SpecialAppointmentResponseDTO();
        responseDTO.setId(response.getPatient().getPatientID());
        responseDTO.setAppointmentDate(response.getAppointmentDate());
        responseDTO.setAppointmentHour(response.getAppointmentHour());
        responseDTO.setId(response.getId());
        return responseDTO;
    }

    public AvaibleAppointmentResponseDTO mapFromRequestToResponseOfAppointmentSlot(AppointmentSlot slot){
        AvaibleAppointmentResponseDTO response = new AvaibleAppointmentResponseDTO();
        response.setId(slot.getId());
        response.setToday(slot.getToday());
        response.setMinHour(slot.getMinHour());
        response.setStatus(slot.getStatus());
        response.setDoctor_id(slot.getDoctor_id().getDoctorID());
        return response;
    }

    public List<AvaibleAppointmentResponseDTO> mapIterableOfSlotDTO(Iterable<AppointmentSlot> list){
        List<AvaibleAppointmentResponseDTO> response = new ArrayList<>();
        for (AppointmentSlot slot : list){
            response.add(mapFromRequestToResponseOfAppointmentSlot(slot));
        }
        return response;
    }

   public RefertResponseDTO mapFromRefertToResponseDTO(PatientRefert request){
        return new RefertResponseDTO(request.getId(), request.getPatient().getPatientID(),
                request.getEmissionDate(), request.getDiagnosis());
   }
   public List<RefertResponseDTO> mapFromIterableToRefertResponseListDTO(Iterable<PatientRefert> list){
        List<RefertResponseDTO> response = new ArrayList<>();
        for (PatientRefert refert : list){
            response.add(mapFromRefertToResponseDTO(refert));
        }
        return response;
   }

    public AppointmentResponseDTO mapFromRequestObjectToResponseDTO(Requests request){
        return new AppointmentResponseDTO(
                request.getId(), request.getPatient().getPatientID(), request.getDay(),
                request.getHour(), request.getDescription()
        );

    }
}
