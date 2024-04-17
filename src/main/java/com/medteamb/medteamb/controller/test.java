package com.medteamb.medteamb.controller;

import com.medteamb.medteamb.model.Appointment;
import com.medteamb.medteamb.model.Calendar.SingleAgenda;
import com.medteamb.medteamb.model.Doctor;
import com.medteamb.medteamb.model.Patient;

import com.medteamb.medteamb.service.AgendService;
import com.medteamb.medteamb.service.PatientService;

import com.medteamb.medteamb.service.ResponseHandler.PatientResponse.PatientResponse;
import com.medteamb.medteamb.service.ResponseHandler.PatientResponse.PatientResponseIterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class test {
    @Autowired
    private PatientService service;

    @Autowired
    private AgendService service2;
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
    public Iterable<Patient> getPatientsIds(@RequestBody Iterable<Integer> ids){
        return service.getPatientsByIds(ids);
    }
    @GetMapping("/patient/appointments")
    public Iterable<Appointment> getAllAppointments(@RequestParam Integer id){
        return service.getAllAppointment(id);
    }


    @PostMapping("/newAgend")
    public SingleAgenda newAgend(@RequestBody Doctor doc){
        return service2.saveNewAgend(doc);
    }



}
