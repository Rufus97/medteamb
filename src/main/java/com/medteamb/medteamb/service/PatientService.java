package com.medteamb.medteamb.service;


import com.medteamb.medteamb.model.Appointment;
import com.medteamb.medteamb.model.Patient;
import com.medteamb.medteamb.repository.PatientRepository;
import com.medteamb.medteamb.service.DTO.patientDTO.DTOmapper;
import com.medteamb.medteamb.service.DTO.patientDTO.PatientDTO;
import com.medteamb.medteamb.service.DTO.patientDTO.PatientDTOList;
import com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions.PatientNotSavedException;
import com.medteamb.medteamb.service.ResponseHandler.PatientResponse.PatientResponse;
import com.medteamb.medteamb.service.ResponseHandler.PatientResponse.PatientResponseIterables;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

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
        return new PatientResponse(mapper.mapFromPatientToResponse(patientRepo.save(newPatient)));
    }

    //READ
    // get all appointment history
    public Iterable<Appointment> getAllAppointment(Integer id){
      return patientRepo.getAllPatientAppointments(id);
    }
    // get patient by id
    public PatientResponse getPatient(Integer id){
       return new PatientResponse( mapper.mapFromPatientToResponse(patientRepo.getById(id)));
    }
    // serve un dto per la lista
    public PatientResponseIterables getPatientsByIds(Iterable<Integer> ids){
         return new PatientResponseIterables(new PatientDTOList(patientRepo.findAllById(ids)));
    }
    //UPDATE
    public PatientResponse updatePatientById(Patient newPatient, Integer id){
       Patient patient = patientRepo.findById(id).get();
       patient.updateThisPatient(newPatient);
       return new PatientResponse(new PatientDTO(patientRepo.save(patient)));
    }
    //DELETE
    public PatientResponse deletePatientById(Integer id){
        Patient patient = patientRepo.findById(id).get();
        patientRepo.delete(patient);
        return new PatientResponse(new PatientDTO(patient));
    }

}





