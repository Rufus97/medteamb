package com.medteamb.medteamb.controller;

import com.medteamb.medteamb.model.Appointment;
import com.medteamb.medteamb.model.Patient;
import com.medteamb.medteamb.service.DTO.patientDTO.PatientResponseDTO;
import com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions.PatientNotSavedException;
import com.medteamb.medteamb.service.PatientService;
import com.medteamb.medteamb.service.ResponseHandler.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
public class test {
    @Autowired
    private PatientService service;
    @PostMapping("/postPatient")
    public GenericResponse<PatientResponseDTO> savePatient(@RequestBody Patient patient){
        return service.newPatient(patient);
    }
    @GetMapping("/patient")
    public GenericResponse<PatientResponseDTO> getPatient(@RequestParam Integer id){
        return service.getPatient(id);
    }

    @GetMapping("/patient/appointments")
    public GenericResponse<Iterable<Appointment>> getAllAppointments(@RequestParam Integer id){
        return service.getAllAppointment(id);
    }
    @GetMapping("/patients/byID")
    public GenericResponse<Iterable<Patient>> getAllPatientsId(@RequestBody Iterable<Integer> ids){
        return service.getPatientsById(ids);
    }
    @DeleteMapping("/patient")
    public GenericResponse<PatientResponseDTO> deletePatientById(@RequestParam Integer id){
        return service.deletePatientById(id);
    }


}
