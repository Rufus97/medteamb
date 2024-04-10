package com.medteamb.medteamb.controller;

import com.medteamb.medteamb.model.Appointment;
import com.medteamb.medteamb.model.Patient;
import com.medteamb.medteamb.service.DTO.patientDTO.PatientRequestBodyDTO;
import com.medteamb.medteamb.service.DTO.patientDTO.PatientResponseDTO;
import com.medteamb.medteamb.service.ExceptionHandler.ObjectNotFoundException;
import com.medteamb.medteamb.service.PatientService;
import com.medteamb.medteamb.service.ResponseHandler.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    public GenericResponse<Iterable<Patient>> getAllPatientsId(@RequestParam Iterable<Integer> ids){
        return service.getPatientsById(ids);
    }
    @DeleteMapping("/patient")
    public GenericResponse<PatientResponseDTO> getAllPatientsId(@RequestParam Integer id){
        return service.deletePatientById(id);
    }


}
