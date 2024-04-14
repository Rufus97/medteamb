package com.medteamb.medteamb.service;

import com.medteamb.medteamb.model.Appointment;
import com.medteamb.medteamb.model.Patient;
import com.medteamb.medteamb.repository.PatientRepository;
import com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions.PatientNotFound;
import com.medteamb.medteamb.service.ResponseHandler.PatientResponse.PatientResponse;
import com.medteamb.medteamb.service.ResponseHandler.PatientResponse.PatientResponseIterables;
import com.medteamb.medteamb.service.dto.patient.*;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    PatientRepository patientRepo;
    DTOmapper mapper;

    ListOfDTOmapper listMapper;

    public PatientService(PatientRepository patientRepo, DTOmapper mapper, ListOfDTOmapper listMapper){
        this.patientRepo = patientRepo;
        this.mapper = mapper;
        this.listMapper = listMapper;
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
               orElseThrow(() -> new PatientNotFound("patient not found")));
       return new PatientResponse(response);
    }
    // get more patients from ids
    public Iterable<Patient> getPatientsByIds(Iterable<Integer> ids){
        return patientRepo.findAllById(ids);
    }
    //UPDATE

    // update 1 patient by id with a newPatientIstance
    public PatientResponse updatePatientById(Patient newPatient, Integer id){
       Patient patient = patientRepo.findById(id).
               orElseThrow(() -> new PatientNotFound("patient not found"));
       patient.updateThisPatient(newPatient);
       return new PatientResponse(mapper.mapFromPatientToResponse(patient));
    }
    //DELETE

    // delete 1 patient by id
    public PatientResponse deletePatientById(Integer id){
        Patient patient = patientRepo.findById(id).
                orElseThrow(() -> new PatientNotFound("patient not found"));
        patientRepo.delete(patient);
        return new PatientResponse(mapper.mapFromPatientToResponse(patient));
    }

}





