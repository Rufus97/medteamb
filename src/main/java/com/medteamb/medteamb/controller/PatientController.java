package com.medteamb.medteamb.controller;

import com.medteamb.medteamb.service.dto.patient.DTOmapper;
import com.medteamb.medteamb.service.dto.patient.PatientDTO;
import com.medteamb.medteamb.service.dto.patient.PatientRequestBodyDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medteamb.medteamb.model.Appointment;
import com.medteamb.medteamb.model.Patient;
import com.medteamb.medteamb.service.PatientService;
import com.medteamb.medteamb.service.ResponseHandler.PatientResponse.PatientResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
	
	private DTOmapper dtoMapper;
    private PatientService service;

    public PatientController (PatientService service, DTOmapper dtoMapper) {
    	this.dtoMapper = dtoMapper; 
    	this.service = service;
    }

    @PostMapping("/create")
    public PatientResponse savePatient(@RequestBody Patient patient){
        return service.newPatient(patient);
    }
    @GetMapping("/get/{patientID}")
    public PatientResponse getPatient(@PathVariable Integer patientID){
        return service.getPatient(patientID);
    }
    @GetMapping("/getMultiple")
    public Iterable<Patient> getPatientsIds(@RequestBody Iterable<Integer> patientIDs){
        return service.getPatientsByIds(patientIDs);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> patients = service.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
    @GetMapping("/get/{patientID}/appointments")
    public Iterable<Appointment> getAllAppointments(@PathVariable Integer patientID) {
        return service.getAllAppointment(patientID);
    }
    @PutMapping("/update/{patientID}")
    public PatientResponse updatePatientById(@RequestBody PatientRequestBodyDTO newPatient, @PathVariable Integer patientID){
        return service.updatePatientById(dtoMapper.requestToPatientMapping(newPatient) , patientID);
    }
    @DeleteMapping("/delete/{patientID}")
    public PatientResponse deletePatientById(@PathVariable Integer patientID){
        return service.deletePatientById(patientID);
    }

}
