package com.medteamb.medteamb.service;

import com.medteamb.medteamb.model.Appointment;
import com.medteamb.medteamb.model.Patient;
import com.medteamb.medteamb.repository.PatientRepository;
import com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions.PatientResponseException;
import com.medteamb.medteamb.service.ResponseHandler.PatientResponse.PatientResponse;
import com.medteamb.medteamb.service.ResponseHandler.PatientResponse.PatientResponseIterables;
import com.medteamb.medteamb.service.dto.patient.*;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    PatientRepository patientRepo;
    DTOmapper mapper;

    public PatientService(PatientRepository patientRepo, DTOmapper mapper){
        this.patientRepo = patientRepo;
        this.mapper = mapper;
    }

    //CREATE
    public PatientResponse newPatient(Patient newPatient){
       return new PatientResponse(mapper.mapFromPatientToResponse(patientRepo.save(newPatient))) ;
    }

    //READ
    // get all appointment history
    public Iterable<Appointment> getAllAppointment(Integer id){
        return patientRepo.getAllPatientAppointments(id);
    }
    // get patient by id
    public PatientResponse getPatient(Integer id){
       PatientDTO response =  mapper.mapFromPatientToResponse(patientRepo.findById(id).
               orElseThrow(() -> new PatientResponseException("patient not found")));
       return new PatientResponse(response);
    }
    // serve un dto per la lista
    public PatientResponseIterables getPatientsByIds(Iterable<Integer> ids){
         return new PatientResponseIterables(patientRepo.findAllById(ids));
    }
    //UPDATE
    public PatientResponse updatePatientById(Patient newPatient, Integer id){
       Patient patient = patientRepo.findById(id).get();
       patient.updateThisPatient(newPatient);
       return new PatientResponse(mapper.mapFromPatientToResponse(patient));
    }
    //DELETE
    public PatientResponse deletePatientById(Integer id){
        Patient patient = patientRepo.findById(id).get();
        patientRepo.delete(patient);
        return new PatientResponse(mapper.mapFromPatientToResponse(patient));
    }

}





