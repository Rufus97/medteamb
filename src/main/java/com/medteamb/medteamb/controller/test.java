package com.medteamb.medteamb.controller;


import com.medteamb.medteamb.model.Calendar.AppointmentSlot;
import com.medteamb.medteamb.model.Patient.Patient;

import com.medteamb.medteamb.service.DoctorService;
import com.medteamb.medteamb.service.PatientService;

import com.medteamb.medteamb.service.ResponseHandler.PatientResponse.PatientResponse;
import com.medteamb.medteamb.service.dto.doctor.DoctorRequestDTO;
import com.medteamb.medteamb.service.dto.patient.DateDTO;
import com.medteamb.medteamb.service.dto.patient.PatientAppointmentDTO.PatientRequestAppointmentDTO;
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
    @PostMapping("/patient")
    public PatientResponse savePatient(@RequestBody PatientRequestDTO patient){
        return service.newPatient(patient);
    }


    @PostMapping("/doctor")
    public DoctorRequestDTO postDoctor(@RequestBody DoctorRequestDTO docDto){return serviceDoc.saveDoctor(docDto);}
    //READ

    @GetMapping("/patientById")
    public PatientResponse getPatient(@RequestParam Long id){
        return service.getPatient(id);
    }
    @GetMapping("/patients/byIds")
    public Iterable<Patient> getPatientsIds(@RequestBody Iterable<Long> ids){
        return service.getPatientsByIds(ids);
    }
    @GetMapping("/patient/appointments")
    public Iterable<AppointmentSlot> getAllAppointments(@RequestParam Integer id){
        return service.getAllAppointmentsOfOnePatient(id);
    }
    @GetMapping("/patient/appointments/{id}")
    public AppointmentSlot getOneAppointmentByIdAndDate(@RequestBody PatientRequestAppointmentDTO dto, @PathVariable Integer id){
        return service.getOneAppointmentFromPatientID(dto, id);
    }

    //DELETE
    @DeleteMapping("/patient")
    public PatientResponse deletePatientById(@RequestParam Long id){
        return service.deletePatientById(id);
    }
    //UPDATE
     @PutMapping("/patient/{id}")
    public PatientResponse updatePatientById(@RequestBody Patient newPatient, @PathVariable Long id){
        return service.updatePatientById(newPatient, id);
    }


}
