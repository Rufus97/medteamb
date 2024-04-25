package com.medteamb.medteamb.controller;

import com.medteamb.medteamb.model.AppointmentStatus;
import com.medteamb.medteamb.model.Calendar.AppointmentSlot;
import com.medteamb.medteamb.model.Requests;
import com.medteamb.medteamb.repository.PatientRepository;
import com.medteamb.medteamb.repository.PatientRequestsRepository;
import com.medteamb.medteamb.service.PatientService;
import com.medteamb.medteamb.service.dto.patient.PatientAppointmentDTO.RequestForNewAppointmentDTO;
import com.medteamb.medteamb.service.dto.patient.PatientAppointmentDTO.RequestToCancelAppointmentDTO;
import com.medteamb.medteamb.service.dto.patient.PatientAppointmentDTO.RequestToMoveAppointmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {
     @Autowired
     PatientRepository patientRepo;
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
     public Requests askForAppointment(@RequestBody RequestForNewAppointmentDTO request){
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
     public Iterable<AppointmentSlot> getMyAppointmentHistory(Integer patientID){
          return service.getAppointmentHistory(patientID);
     }

}

/*
▪
▪
▪ poter ricevere una conferma dell'appuntamento via email
▪
▪ poter richiedere farmaci ed impegnative di visite specialistiche
▪ poter ricevere prescrizioni di farmaci ed impegnative per visite specialistiche
▪ poter caricare i miei referti
     */
