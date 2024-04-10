package com.medteamb.medteamb.service;

import com.medteamb.medteamb.model.Patient;
import com.medteamb.medteamb.repository.PatientRepository;
import com.medteamb.medteamb.service.DTO.patientDTO.DTOmapper;
import com.medteamb.medteamb.service.DTO.patientDTO.PatientRequestBodyDTO;
import com.medteamb.medteamb.service.DTO.patientDTO.PatientResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    PatientRepository patientRepo;
    DTOmapper mapper;

    public PatientService(PatientRepository patientRepo, DTOmapper mapper){
        this.patientRepo = patientRepo;
        this.mapper = mapper;
    }


    public Integer newPatient(Patient newPatient){

       Patient savedPatient = patientRepo.save(newPatient);
       return savedPatient.getPatientID();

    }

}
