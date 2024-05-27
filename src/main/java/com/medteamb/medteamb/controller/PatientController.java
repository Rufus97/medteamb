package com.medteamb.medteamb.controller;

import com.medteamb.medteamb.Security.utils.PatientRoleAnnotation;
import com.medteamb.medteamb.model.patient.Patient;
import com.medteamb.medteamb.service.PatientService;
import com.medteamb.medteamb.service.ResponseHandler.Response;
import com.medteamb.medteamb.service.ResponseHandler.ResponseForLists;
import com.medteamb.medteamb.service.dto.appointment.AppointmentResponseDTO;
import com.medteamb.medteamb.service.dto.patient.*;
import com.medteamb.medteamb.service.dto.patient.RefertDTO.RefertResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

     @Autowired
     PatientService service;

     //poter visualizzare le disponibilità del mio dottore // testato
     @PatientRoleAnnotation
     @GetMapping("/docAvailability/{page}")
     public ResponseForLists<AppointmentResponseDTO> getAvaibleAppointmentsByDocId(@PathVariable int page, @RequestParam Long docID, @RequestParam int size){
         return service.getDocAvailabilityById(docID, page, size);
     }
     @PatientRoleAnnotation
     @GetMapping("/docAvaibilityByName/{page}")  //testato
     public ResponseForLists<AppointmentResponseDTO> getAvaibleAppointmentsByDocNameAndSurname(@RequestParam String name, @RequestParam String surname, @PathVariable int page, @RequestParam int size){
          return service.getDocAvailabilityByNameAndSurname(name, surname, page, size);
     }
     
     // poter prenotare un appuntamento online con il mio dottore, specificando data, ora e motivo della  visita
     //testato - funziona selezionando l'appuntamento da prenotare e dando il proprio id
     @PatientRoleAnnotation
     @PutMapping("/newAppointment")
     public Response<AppointmentResponseDTO> askForAppointment(@RequestAttribute Long id,  @RequestParam Integer appointmentID){
           return service.newAppointmentRequest(id, appointmentID);
     }

     //poter chiedere di spostare l’appuntamento esistente ove possibile
     @PatientRoleAnnotation
     @PutMapping("/moveAppointment")
     public Response<AppointmentResponseDTO> moveAppointment(@RequestAttribute Long id,  @RequestBody PatientUpdateAppointment request){
         return service.moveAppointment(id, request);
     }

     //poter annullare un appuntamento esistente
     @PatientRoleAnnotation
     @PutMapping("/cancelAppointment")
     public Response<AppointmentResponseDTO> cancelAppointment(@RequestAttribute Long id,  @RequestParam Integer appointmentID){
          return service.cancelAppointment(id, appointmentID);
     }
     //poter visualizzare lo storico delle mie visite e i relativi referti ( REFERTI DA CONCEPIRE )
     @PatientRoleAnnotation
     @GetMapping("/myAppointments/{page}")
     public ResponseForLists<AppointmentResponseDTO> getMyAppointmentHistory(@RequestAttribute Integer id, @PathVariable int page, @RequestParam int size){
          return service.getAppointmentHistoryByPatientId(id, page, size);
     }
     // poter visualizzare gli appuntamenti prenotati da svolgere
     @PatientRoleAnnotation
     @GetMapping("/myAppointmentsToDo/{page}")
     public ResponseForLists<AppointmentResponseDTO> getMyAppointmentToDo(@RequestAttribute Integer id, @PathVariable int page, @RequestParam int size){
          return service.getAppointmentsToDoByPatientId(id, page, size);
     }
     //poter caricare i miei referti
     @PatientRoleAnnotation
     @GetMapping("/myReferts/{page}")
     public ResponseForLists<RefertResponseDTO> getHistoryOfMyReferts(@RequestAttribute Long id, @PathVariable int page, @RequestParam int size){
          return service.getRefertHistoryByPatientId(id, page, size);
     }
     @PatientRoleAnnotation
     @PostMapping("/postRefert")
     public Response<RefertResponseDTO> postRefert(@RequestAttribute Long id, @RequestBody String diagnosis){
          return service.postTestRefert(id, diagnosis);
     }



     //CREATE
     @PatientRoleAnnotation
     @PostMapping("/create")
     public Response<PatientResponseDTO> registerPatient(@RequestBody RegisterPatientDTO patient) throws AuthenticationException {
          return service.newPatient(patient);
     }


     //READ
     @PatientRoleAnnotation
     @GetMapping("/ById")
     public Response<Patient> getPatient(@RequestAttribute Long id){
          return service.getPatient(id);
     }

     //DELETE
     @PatientRoleAnnotation
     @DeleteMapping
     public Response<PatientResponseDTO> deletePatientById(@RequestAttribute Long id){
          return service.deletePatientById(id);
     }
     //UPDATE
     @PatientRoleAnnotation
     @PutMapping("/{id}")
     public Response<PatientResponseDTO> updatePatientById(@RequestBody Patient newPatient, @RequestAttribute Long id){
          return service.updatePatientById(newPatient, id);
     }

}

/*
▪ poter ricevere una conferma dell'appuntamento via email
▪ poter ricevere prescrizioni di farmaci ed impegnative per visite specialistiche
*/

