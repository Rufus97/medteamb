package com.medteamb.medteamb.controller;

import com.medteamb.medteamb.model.patient.Patient;
import com.medteamb.medteamb.model.patient.Requests;
import com.medteamb.medteamb.service.PatientService;
import com.medteamb.medteamb.service.ResponseHandler.PatientResponse.PatientResponse;
import com.medteamb.medteamb.service.ResponseHandler.ResponseForLists;
import com.medteamb.medteamb.service.dto.appointment.AppointmentResponseDTO;
import com.medteamb.medteamb.service.dto.patient.PatientRequestDTO;
import com.medteamb.medteamb.service.dto.patient.PatientUpdateAppointment;
import com.medteamb.medteamb.service.dto.patient.PatientRequestAppointment;
import com.medteamb.medteamb.service.dto.patient.RefertDTO.RefertResponseDTO;
import com.medteamb.medteamb.service.dto.patient.SpecialAppointments.SpecialAppointmentRequestDTO;
import com.medteamb.medteamb.service.dto.patient.SpecialAppointments.SpecialAppointmentResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

     @Autowired
     PatientService service;

     //poter visualizzare le disponibilità del mio dottore // testato
     @GetMapping("/docAvailability/{page}")
     public ResponseForLists<AppointmentResponseDTO> getAvaibleAppointmentsByDocId(@PathVariable int page, @RequestParam Long docID, @RequestParam int size){
         return service.getDocAvailabilityById(docID, page, size);
     }
     
     @GetMapping("/docAvaibilityByName/{page}")  //testato
     public ResponseForLists<AppointmentResponseDTO> getAvaibleAppointmentsByDocNameAndSurname(@RequestParam String name, @RequestParam String surname, @PathVariable int page, @RequestParam int size){
          return service.getDocAvailabilityByNameAndSurname(name, surname, page, size);
     }
     
     // poter prenotare un appuntamento online con il mio dottore, specificando data, ora e motivo della  visita
     //testato - funziona selezionando l'appuntamento da prenotare e dando il proprio id
     @PutMapping("/newAppointment")
     public AppointmentResponseDTO askForAppointment(@RequestBody PatientRequestAppointment patientRequestAppointment){
           return service.newAppointmentRequest(patientRequestAppointment);
     }

     //poter chiedere di spostare l’appuntamento esistente ove possibile
     @PutMapping("/moveAppointment")
     public AppointmentResponseDTO moveAppointment(@RequestBody PatientUpdateAppointment patientUpdateAppointment){
         return service.moveAppointment(patientUpdateAppointment);
     }
     
     
     //poter annullare un appuntamento esistente
     @PutMapping("/cancelAppointment")
     public Requests cancelAppointment(){
          return null;
     }
     //poter visualizzare lo storico delle mie visite e i relativi referti ( REFERTI DA CONCEPIRE )
     @GetMapping("/myAppointments/{page}")
     public ResponseForLists<AppointmentResponseDTO> getMyAppointmentHistory(@RequestParam Integer patientID, @PathVariable int page, @RequestParam int size){
          return service.getAppointmentHistoryByPatientId(patientID, page, size);
     }
     // poter visualizzare gli appuntamenti prenotati da svolgere
     @GetMapping("/myAppointmentsToDo/{page}")
     public ResponseForLists<AppointmentResponseDTO> getMyAppointmentToDo(@RequestParam Integer patientID, @PathVariable int page, @RequestParam int size){
          return service.getAppointmentsToDo(patientID, page, size);
     }
     //poter caricare i miei referti
     @GetMapping("/myReferts/{page}")
     public ResponseForLists<RefertResponseDTO> getHistoryOfMyReferts(@RequestParam Long id, @PathVariable int page, @RequestParam int size){
          return service.getRefertHistoryByPatientId(id, page, size);
     }
     // poter richiedere farmaci ed impegnative di visite specialistiche
     @PostMapping("/newSpecial")
     public SpecialAppointmentResponseDTO askForNewSpecialAppointment(@RequestBody SpecialAppointmentRequestDTO requestDTO) {
          return service.newSpecialAppointmentRequest(requestDTO);
     }

     //CREATE
     @PostMapping("/create")
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

     @PostMapping("/testPostRefert")
     public RefertResponseDTO postTestRefert(@RequestParam Long id, @RequestBody String diagnosis){
         return service.postTestRefert(id, diagnosis);
     }
}

/*
▪ poter ricevere una conferma dell'appuntamento via email
▪ poter ricevere prescrizioni di farmaci ed impegnative per visite specialistiche
*/

