package com.medteamb.medteamb.service.dto;

import com.medteamb.medteamb.model.agenda.Appointment;
import com.medteamb.medteamb.model.patient.Patient;
import com.medteamb.medteamb.model.patient.PatientRefert;
import com.medteamb.medteamb.model.patient.Requests;
import com.medteamb.medteamb.model.patient.SpecialAppointments;
import com.medteamb.medteamb.repository.DoctorRepository;
import com.medteamb.medteamb.repository.SecretaryRepository;
import com.medteamb.medteamb.repository.patient.PatientRepository;
import com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions.NotFound;
import com.medteamb.medteamb.service.dto.appointment.AppointmentRequestDTO;
import com.medteamb.medteamb.service.dto.appointment.AppointmentResponseDTO;
import com.medteamb.medteamb.service.dto.patient.PatientRequestDTO;
import com.medteamb.medteamb.service.dto.patient.PatientResponseDTO;
import com.medteamb.medteamb.service.dto.patient.RefertDTO.RefertResponseDTO;
import com.medteamb.medteamb.service.dto.patient.SpecialAppointments.SpecialAppointmentResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DTOmapper{

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    SecretaryRepository secretaryRepository;

    // patient response mapper
    public PatientResponseDTO mapFromPatientToResponse(Patient response){
        PatientResponseDTO responseDTO = new PatientResponseDTO();
        responseDTO.setId(response.getPatientID());
        responseDTO.setPatientName(response.getPatientName());
        responseDTO.setPatientSurname(response.getPatientSurname());
        responseDTO.setTaxCode(response.getTaxCode());
        return responseDTO;
    }
    // patient request mapper
    public Patient mapFromRequestToPatient(PatientRequestDTO request){
       return new Patient(request.getPatientName(),
               		request.getPatientSurname(),
               		request.getPatientPhoneNumber(),
               		request.getTaxCode(),
       				request.getPatientEmail());
    }


   // list of appointments mapper

    public List<AppointmentResponseDTO> mapIterableOfSlotDTO(Iterable<Appointment> list){
        List<AppointmentResponseDTO> response = new ArrayList<>();
        for (Appointment slot : list){
           response.add(mapFromAppointmentToResponseDTO(slot));
        }
        return response;
    }

    public AppointmentResponseDTO mapFromAppointmentToResponseDTO(Appointment obj){
       AppointmentResponseDTO response = new AppointmentResponseDTO();
       response.setLocation(obj.getLocation()); response.setAppointmentDateTime(obj.getAppointmentDateTime());
       response.setTaxCode(obj.getTaxCode()); response.setMedicalService(obj.getMedicalService());
       response.setStatus(obj.getStatus()); response.setPatient(obj.getPatient());
       response.setDoctor(obj.getDoctor()); response.setSecretary(obj.getSecretary());
       return response;
    }

    public Appointment mapFromAppointmentRequestToAppointment(AppointmentRequestDTO request){
        Appointment goodRequest = new Appointment();
        goodRequest.setPatient(patientRepository.findById(request.getPatient()).orElseThrow(
                ()-> new NotFound("patient with id: " + request.getPatient() + " not found")));
        goodRequest.setSecretary(secretaryRepository.findById(request.getSecretary()).orElseThrow(
                ()-> new NotFound("secretary with id: " + request.getSecretary() + " not found")
        ));
        goodRequest.setDoctor(doctorRepository.findById(request.getDoctor()).orElseThrow(
                ()-> new NotFound("doctor with id: " + request.getDoctor() + " not found")
        )); goodRequest.setStatus(request.getStatus()); goodRequest.setMedicalService(request.getMedicalService());
        goodRequest.setTaxCode(request.getTaxCode()); goodRequest.setAppointmentDateTime(request.getAppointmentDateTime());
        goodRequest.setLocation(request.getLocation());
        return goodRequest;
    }




   // refert dto mappers
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

    // special appointments if required entity
    public SpecialAppointmentResponseDTO mapFromSpecialRequest(SpecialAppointments response){
        SpecialAppointmentResponseDTO responseDTO = new SpecialAppointmentResponseDTO();
        responseDTO.setId(response.getPatient().getPatientID());
        responseDTO.setAppointmentDate(response.getAppointmentDate());
        responseDTO.setAppointmentHour(response.getAppointmentHour());
        responseDTO.setId(response.getId());
        return responseDTO;
    }
}
