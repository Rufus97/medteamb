package com.medteamb.medteamb.service.dto.patient.PatientAppointmentDTO;

import com.medteamb.medteamb.model.Patient.Requests;
import org.springframework.stereotype.Component;

@Component
public class PatientAppointmentMapper {



    public Requests mapFromRequest(RequestForNewAppointmentDTO request){
        Requests newAppointmentRequest = new Requests();
        newAppointmentRequest.setDay(request.getDay());
        newAppointmentRequest.setHour(request.getHour());
        newAppointmentRequest.setDescription(request.getDescription());
        newAppointmentRequest.setId(request.getPatient_id());
        return newAppointmentRequest;
    }

    public Requests mapToBeMovedRequest(RequestToMoveAppointmentDTO request){
        Requests newAppointmentRequest = new Requests();
        newAppointmentRequest.setDay(request.getDay());
        newAppointmentRequest.setHour(request.getHour());
        newAppointmentRequest.setDescription(request.getDescription());
        newAppointmentRequest.setNewDate(request.getNewDay());
        return newAppointmentRequest;
    }
}
