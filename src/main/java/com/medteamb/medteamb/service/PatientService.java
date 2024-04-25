package com.medteamb.medteamb.service;

import com.medteamb.medteamb.model.Calendar.AppointmentSlot;
import com.medteamb.medteamb.model.Patient;
import com.medteamb.medteamb.model.Requests;
import com.medteamb.medteamb.repository.PatientRepository;
import com.medteamb.medteamb.repository.PatientRequestsRepository;
import com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions.PatientConflictException;
import com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions.PatientNotFound;
import com.medteamb.medteamb.service.ResponseHandler.PatientResponse.PatientResponse;
import com.medteamb.medteamb.service.dto.patient.*;
import com.medteamb.medteamb.service.dto.patient.PatientAppointmentDTO.PatientAppointmentMapper;
import com.medteamb.medteamb.service.dto.patient.PatientAppointmentDTO.PatientRequestAppointmentDTO;
import com.medteamb.medteamb.service.dto.patient.PatientAppointmentDTO.RequestForNewAppointmentDTO;
import com.medteamb.medteamb.service.dto.patient.PatientAppointmentDTO.RequestToMoveAppointmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {

    PatientRepository patientRepo;
    PatientRequestsRepository requestsForAppointmentsRepo;
    DTOmapper mapper;

    PatientAppointmentMapper mapperForAppointment;
  /*

▪ poter prenotare un appuntamento online con il mio dottore, specificando data, ora e motivo della
visita
▪ poter chiedere di spostare l’appuntamento esistente ove possibile
▪ poter annullare un appuntamento esistente
▪ poter ricevere una conferma dell'appuntamento via email
▪ poter visualizzare lo storico delle mie visite e i relativi referti
▪ poter richiedere farmaci ed impegnative di visite specialistiche
▪ poter ricevere prescrizioni di farmaci ed impegnative per visite specialistiche
▪ poter caricare i miei referti
   */
    public PatientService(PatientRepository patientRepo, PatientRequestsRepository requestsForAppointmentsRepo,
                          DTOmapper mapper, PatientAppointmentMapper mapperForAppointment){
        this.patientRepo = patientRepo;
        this.requestsForAppointmentsRepo = requestsForAppointmentsRepo;
        this.mapper = mapper;
        this.mapperForAppointment = mapperForAppointment;
    }

    //CREATE
    public PatientResponse newPatient(PatientRequestDTO newPatient){
        checkIfExists(newPatient);
        return new PatientResponse(mapper.mapFromPatientToResponse
                (patientRepo.save(mapper.mapFromRequestToPatient(newPatient))));
    }
    // patient create appointment by day and cause
     public PatientRequestAppointmentDTO createNewAppointmentByDateAndID(PatientRequestAppointmentDTO request, Integer docID){
        PatientRequestAppointmentDTO response = new PatientRequestAppointmentDTO(request.getPatientID(),request.getAppointmentDate(), request.getMessage());
        patientRepo.createAppointmentWithDateAndHour(request.getPatientID(), request.getAppointmentDate().toLocalDate(), request.getAppointmentDate().toLocalTime(), docID);
        return response;
     }
     public Requests newRequestForAppointment(RequestForNewAppointmentDTO request){
        return requestsForAppointmentsRepo.save(mapperForAppointment.mapFromRequest(request));
     }


    //READ

    // get patient by id
    public PatientResponse getPatient(Integer id){
       PatientResponseDTO response =  mapper.mapFromPatientToResponse(patientRepo.findById(id).
               orElseThrow(() -> new PatientNotFound("patient not found")));
       return new PatientResponse(response);
    }
    // get more patients from ids
    public Iterable<Patient> getPatientsByIds(Iterable<Integer> ids){
        return patientRepo.findAllById(ids);
    }

    // verificare disponibilità mio dottore
    // per id
    public Iterable<AppointmentSlot> getDocAvaibilityById(Integer docID){
        return patientRepo.getALlAvaibleAppointmentsOfOneDoctor(docID);
    }
    // per nome e cognome
    public Iterable<AppointmentSlot> getDocAvaibilityByNameAndSurname(String docName, String docSurname){
        return patientRepo.getAllAvaibleAppointmentsOfOneDocNameAndSurname(docName, docSurname);
    }

    public Iterable<AppointmentSlot>  getAllAppointmentsOfOnePatient(Integer id){
        return patientRepo.getAllPatientAppointments(id);
    }
    // read 1 appointment by date and patient id
    public AppointmentSlot  getOneAppointmentFromPatientID(PatientRequestAppointmentDTO request, Integer id){
        return patientRepo.getOneAppointmentFromPatientIdAndDate(
                request.getAppointmentDate().toLocalDate(),
                request.getAppointmentDate().toLocalTime(), id).get();
    }

    //UPDATE
    // update 1 patient by id with a newPatientIstance
    public PatientResponse updatePatientById(Patient newPatient, Integer id){
       Patient patient = patientRepo.findById(id).
               orElseThrow(() -> new PatientNotFound("patient not found"));
       patient.updateThisPatient(newPatient);
       patientRepo.save(patient);
       return new PatientResponse(mapper.mapFromPatientToResponse(patient));
    }
    public Requests updateAppointmentRequestToMove(RequestToMoveAppointmentDTO request){
        Requests oldAppointment = requestsForAppointmentsRepo.findByday(
                request.getDay()).orElseThrow( ()-> new RuntimeException("appointment not found"));
        oldAppointment.setNewDate(request.getNewDay());
        oldAppointment.appointmentToBeMoved();
        return requestsForAppointmentsRepo.save(oldAppointment);
    }

    //DELETE
    // delete 1 patient by id
    public PatientResponse deletePatientById(Integer id){
        Patient patient = patientRepo.findById(id).
                orElseThrow(() -> new PatientNotFound("patient not found"));
        patientRepo.delete(patient);
        return new PatientResponse(mapper.mapFromPatientToResponse(patient));
    }


    // UTILITIES

    public void checkIfExists(PatientRequestDTO request){
        Optional<Patient> optionalPhone = patientRepo.findBypatientPhoneNumber(request.getPatientPhoneNumber());
        if (optionalPhone.isPresent()){
            throw new PatientConflictException("patient phone number " + request.getPatientPhoneNumber()
            + " already exists");
        }
        Optional<Patient> optionalTaxCode = patientRepo.findBytaxCode(request.getTaxCode());
        if (optionalTaxCode.isPresent()){
            throw new PatientConflictException("patient taxCode " + request.getTaxCode()
            + " already exists");
        }
    }
}





