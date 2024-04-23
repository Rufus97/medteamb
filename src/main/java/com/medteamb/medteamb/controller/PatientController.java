package com.medteamb.medteamb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medteamb.medteamb.model.Appointment;
import com.medteamb.medteamb.model.Patient;
import com.medteamb.medteamb.service.DoctorService;
import com.medteamb.medteamb.service.PatientService;
import com.medteamb.medteamb.service.ResponseHandler.PatientResponse.PatientResponse;
import com.medteamb.medteamb.service.dto.doctor.DoctorRequestDTO;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    @Autowired
    private PatientService service;

    @Autowired
    private DoctorService serviceDoc;


    @PostMapping("/create")
    public PatientResponse savePatient(@RequestBody Patient patient){
        return service.newPatient(patient);
    }
    @DeleteMapping("/delete/{patientID}")
    public PatientResponse deletePatientById(@RequestParam Integer patientID){
        return service.deletePatientById(patientID);
    }
    @PutMapping("/update/{patientID}")
    public PatientResponse updatePatientById(@RequestBody Patient newPatient, @PathVariable Integer patientID){
        return service.updatePatientById(newPatient, patientID);
    }
    @GetMapping("/get/{patientID}")
    public PatientResponse getPatient(@RequestParam Integer patientID){
        return service.getPatient(patientID);
    }
    @GetMapping("/getAll")
    public Iterable<Patient> getPatientsIds(@RequestBody Iterable<Integer> patientIDs){
        return service.getPatientsByIds(patientIDs);
    }
    @GetMapping("/get/{patientID}/appointments")
    public Iterable<Appointment> getAllAppointments(@RequestParam Integer patientID){
        return service.getAllAppointment(patientID);
    }


    // perche' c'e' un doctor in questo controller?

    @PostMapping("/doctor")
    public DoctorRequestDTO postDoctor(@RequestBody DoctorRequestDTO docDto){
        return serviceDoc.saveDoctor(docDto);
    }



}
