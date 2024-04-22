package com.medteamb.medteamb.controller;


import com.medteamb.medteamb.model.Calendar.AppointmentSlot;
import com.medteamb.medteamb.model.Doctor;
import com.medteamb.medteamb.model.Patient;

import com.medteamb.medteamb.service.DoctorService;
import com.medteamb.medteamb.service.PatientService;

import com.medteamb.medteamb.service.ResponseHandler.PatientResponse.PatientResponse;
import com.medteamb.medteamb.service.dto.doctor.DoctorRequestDTO;
import com.medteamb.medteamb.service.dto.patient.PatientAppointmentRequestDTO;
import com.medteamb.medteamb.service.dto.patient.PatientRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class test {
    @Autowired
    private PatientService service;

    @Autowired
    private DoctorService serviceDoc;

    //CREATE
    @PostMapping("/patientDTO")
    public PatientResponse savePatient(@RequestBody PatientRequestDTO patient){
        return service.newPatient(patient);
    }

    @PostMapping("/patient")
    public PatientResponse savePatient(@RequestBody Patient patient){
        return service.newPatient(patient);
    }
    @PostMapping("/doctor")
    public DoctorRequestDTO postDoctor(@RequestBody DoctorRequestDTO docDto){return serviceDoc.saveDoctor(docDto);}
    //READ

    @GetMapping("/patientById")
    public PatientResponse getPatient(@RequestParam Integer id){
        return service.getPatient(id);
    }
    @GetMapping("/patients/byIds")
    public Iterable<Patient> getPatientsIds(@RequestBody Iterable<Integer> ids){
        return service.getPatientsByIds(ids);
    }
    @GetMapping("/patient/appointments")
    public Iterable<AppointmentSlot> getAllAppointments(@RequestParam Integer id){
        return service.getAllAppointmentsOfOnePatient(id);
    }
    @GetMapping("/patient/appointments/{id}")
    public AppointmentSlot getOneAppointmentByIdAndDate(@RequestBody PatientAppointmentRequestDTO dto, @PathVariable Integer id){
        return service.getOneAppointmentFromPatientID(dto, id);
    }

    //DELETE
    @DeleteMapping("/patient")
    public PatientResponse deletePatientById(@RequestParam Integer id){
        return service.deletePatientById(id);
    }
    //UPDATE
     @PutMapping("/patient/{id}")
    public PatientResponse updatePatientById(@RequestBody Patient newPatient, @PathVariable Integer id){
        return service.updatePatientById(newPatient, id);
    }





}
