package com.medteamb.medteamb.controller;

import com.medteamb.medteamb.service.PatientService;
import com.medteamb.medteamb.service.dto.patient.PatientRequestDTO;
import com.medteamb.medteamb.service.dto.patient.PatientResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<PatientResponseDTO> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/get/{patientId}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable Integer patientId) {
        PatientResponseDTO patient = patientService.getPatientById(patientId);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PatientResponseDTO> createPatient(@RequestBody PatientRequestDTO patientDTO) {
        PatientResponseDTO createdPatient = patientService.createPatient(patientDTO);
        return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
    }

    @PutMapping("/update/{patientId}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable Integer patientId, @RequestBody PatientRequestDTO patientDTO) {
        PatientResponseDTO updatedPatient = patientService.updatePatient(patientId, patientDTO);
        return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{patientId}")
    public ResponseEntity<Void> deletePatient(@PathVariable Integer patientId) {
        patientService.deletePatient(patientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}