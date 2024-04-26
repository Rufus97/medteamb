package com.medteamb.medteamb.controller;

import com.medteamb.medteamb.model.Calendar.AppointmentSlot;
import com.medteamb.medteamb.model.Patient.Patient;
import com.medteamb.medteamb.model.Patient.PatientRefert;
import com.medteamb.medteamb.model.Patient.Requests;
import com.medteamb.medteamb.model.Patient.SpecialAppointments;
import com.medteamb.medteamb.repository.Patient.PatientRepository;
import com.medteamb.medteamb.service.PatientService;
import com.medteamb.medteamb.service.ResponseHandler.PatientResponse.PatientResponse;
import com.medteamb.medteamb.service.dto.patient.PatientAppointmentDTO.*;
import com.medteamb.medteamb.service.dto.patient.PatientRequestDTO;
import com.medteamb.medteamb.service.dto.patient.SpecialAppointments.SpecialRequestDTO;
import com.medteamb.medteamb.service.dto.patient.SpecialAppointments.SpecialResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patientAppoints")
public class PatientController {

     @Autowired
     PatientService service;

     //poter visualizzare le disponibilità del mio dottore
     @GetMapping("/docAvaibility")
     public Iterable<AppointmentSlot> getAllAvaibleAppointmentByOneDoc(@RequestParam Integer docID){
         return service.getDocAvaibilityById(docID);
     }
     @GetMapping("/docAvaibilityByName")
     public Iterable<AppointmentSlot> getAllAvaibleAppointmentByOneDocNameAndSurname(@RequestParam String name,@RequestParam String surname){
          return service.getDocAvaibilityByNameAndSurname(name, surname);
     }
     // poter prenotare un appuntamento online con il mio dottore, specificando data, ora e motivo della  visita
     @PostMapping("/newAppointment")
     public ResponseForNewAppointmentDTO askForAppointment(@RequestBody RequestForNewAppointmentDTO request){
           return service.newRequestForAppointment(request);
     }

     //poter chiedere di spostare l’appuntamento esistente ove possibile
     @PutMapping("/moveAppointment")
     public Requests askToMoveAppointment(@RequestBody RequestToMoveAppointmentDTO request){
         return service.updateAppointmentRequestToMove(request);
     }
     //poter annullare un appuntamento esistente
     @PutMapping("/cancelAppointment")
     public Requests askToCancelAppointment(@RequestBody RequestToCancelAppointmentDTO request){
          return service.cancelAppointmentRequest(request);
     }
     //poter visualizzare lo storico delle mie visite e i relativi referti ( REFERTI DA CONCEPIRE )
     @GetMapping("/myAppointments")
     public Iterable<AppointmentSlot> getMyAppointmentHistory(@RequestParam Integer patientID){
          return service.getAppointmentHistory(patientID);
     }
     //poter caricare i miei referti
     @GetMapping("/myReferts")
     public Iterable<PatientRefert> getHistoryOfMyReferts(@RequestParam Long id){
          return service.getHistoryOfPatientRefertsByID(id);
     }
     // poter richiedere farmaci ed impegnative di visite specialistiche
     @PostMapping("/newSpecial")
     public SpecialResponseDTO askForNewSpecialAppointment(@RequestBody SpecialRequestDTO requestDTO) {
          return service.newSpecialAppointmentRequest(requestDTO);
     }

     //CREATE
     @PostMapping
     public PatientResponse savePatient(@RequestBody PatientRequestDTO patient){
          return service.newPatient(patient);
     }
     //READ
     @GetMapping("/ById")
     public PatientResponse getPatient(@RequestParam Long id){
          return service.getPatient(id);
     }
     @GetMapping("/byIds")
     public Iterable<Patient> getPatientsIds(@RequestBody Iterable<Long> ids){
          return service.getPatientsByIds(ids);
     }

     //DELETE
     @DeleteMapping
     public PatientResponse deletePatientById(@RequestParam Long id){
          return service.deletePatientById(id);
     }
     //UPDATE
     @PutMapping("/{id}")
     public PatientResponse updatePatientById(@RequestBody Patient newPatient, @PathVariable Long id){
          return service.updatePatientById(newPatient, id);
     }
}

/*
▪ poter ricevere una conferma dell'appuntamento via email
▪ poter ricevere prescrizioni di farmaci ed impegnative per visite specialistiche
*/
