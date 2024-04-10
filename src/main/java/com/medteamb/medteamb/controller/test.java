package com.medteamb.medteamb.controller;

import com.medteamb.medteamb.model.Patient;
import com.medteamb.medteamb.service.DTO.patientDTO.PatientRequestBodyDTO;
import com.medteamb.medteamb.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {
    @Autowired
    private PatientService service;
    @PostMapping("/postPatient")
    public Integer savePatient(@RequestBody Patient patient){
        return service.newPatient(patient);
    }

}
