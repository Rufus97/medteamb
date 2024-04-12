package com.medteamb.medteamb.controller;

import com.medteamb.medteamb.model.Appointment;
import com.medteamb.medteamb.model.Patient;
import com.medteamb.medteamb.service.DTO.patientDTO.PatientDTO;
import com.medteamb.medteamb.service.DTO.patientDTO.PatientDTOList;
import com.medteamb.medteamb.service.PatientService;
import com.medteamb.medteamb.service.ResponseHandler.BaseResponse;
import com.medteamb.medteamb.service.ResponseHandler.PatientResponse.PatientResponse;
import com.medteamb.medteamb.service.ResponseHandler.PatientResponse.PatientResponseIterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class test {
    @Autowired
    private PatientService service;
    @PostMapping("/patient")
    public PatientResponse savePatient(@RequestBody Patient patient){
        return service.newPatient(patient);
    }
    @DeleteMapping("/patient")
    public PatientResponse deletePatientById(@RequestParam Integer id){
        return service.deletePatientById(id);
    }
    @PutMapping("/patient/{id}")
    public PatientResponse updatePatientById(@RequestBody Patient newPatient, @PathVariable Integer id){
        return service.updatePatientById(newPatient, id);
    }
    @GetMapping("/patientById")
    public PatientResponse getPatient(@RequestParam Integer id){
        return service.getPatient(id);
    }
    @GetMapping("/patients/byIds")
    public PatientResponseIterables getPatientsIds(@RequestBody Iterable<Integer> ids){
        return service.getPatientsByIds(ids);
    }
    @GetMapping("/patient/appointments")
    public Iterable<Appointment> getAllAppointments(@RequestParam Integer id){
        return service.getAllAppointment(id);
    }




}
