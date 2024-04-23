package com.medteamb.medteamb.service;

import com.medteamb.medteamb.model.Calendar.AppointmentSlot;
import com.medteamb.medteamb.model.Patient;
import com.medteamb.medteamb.repository.PatientRepository;
import com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions.PatientConflictException;
import com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions.PatientNotFound;
import com.medteamb.medteamb.service.ResponseHandler.PatientResponse.PatientResponse;
import com.medteamb.medteamb.service.dto.patient.*;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    PatientRepository patientRepo;
    DTOmapper mapper;

    ListOfDTOmapper listMapper;

    public PatientService(PatientRepository patientRepo, DTOmapper mapper, ListOfDTOmapper listMapper){
        this.patientRepo = patientRepo;
        this.mapper = mapper;
        this.listMapper = listMapper;
    }

    //CREATE
    public PatientResponse newPatient(PatientRequestDTO newPatient){
        checkIfExists(newPatient);
        return new PatientResponse(mapper.mapFromPatientToResponse
                (patientRepo.save(mapper.mapFromRequestToPatient(newPatient))));
    }

    public Optional<AppointmentSlot> newAppointmentByDate(PatientRequestAppointmentDTO request){

        return patientRepo.createAppointmentWithDateAndHour(request.getPatientID(), request.getAppointmentDate().toLocalDate(),
                request.getAppointmentDate().toLocalTime());
    }


    //READ

    // get patient by id
    public PatientResponse getPatient(Integer id){
       PatientResponseDTO response =  mapper.mapFromPatientToResponse(patientRepo.findById(id).
               orElseThrow(() -> new PatientNotFound("patient not found")));
       return new PatientResponse(response);
    }
    // get more patients from ids
    public Iterable<Patient> getPatientsByIds(Iterable<Integer> ids){
        return patientRepo.findAllById(ids);
    }

    // verificare disponibilità mio dottore
    // possibili più metodi di ricerca, per id, per nome e cognome, per email, per telefono ecc ecc
    public Iterable<AppointmentSlot> getAllAvaibleAppointmentByOneDoc(Integer docID){
        return patientRepo.getALlAvaibleAppointmentsOfOneDoctor(docID);
    }
   /* public Iterable<AppointmentSlot> getAllAvaibleAppointmentByOneDoc(PatientAppointmentRequestDTO dto){

    }*/

    public Iterable<AppointmentSlot>  getAllAppointmentsOfOnePatient(Integer id){
        return patientRepo.getAllPatientAppointments(id);
    }

    public AppointmentSlot  getOneAppointmentFromPatientID(PatientRequestAppointmentDTO request, Integer id){
        return patientRepo.getOneAppointmentFromPatientIdAndDate(
                request.getAppointmentDate().toLocalDate(),
                request.getAppointmentDate().toLocalTime(), id).get();
    }





    //UPDATE

    // update 1 patient by id with a newPatientIstance
    public PatientResponse updatePatientById(Patient newPatient, Integer id){
       Patient patient = patientRepo.findById(id).
               orElseThrow(() -> new PatientNotFound("patient not found"));
       patient.updateThisPatient(newPatient);
       patientRepo.save(patient);
       return new PatientResponse(mapper.mapFromPatientToResponse(patient));
    }
    //DELETE

    // delete 1 patient by id
    public PatientResponse deletePatientById(Integer id){
        Patient patient = patientRepo.findById(id).
                orElseThrow(() -> new PatientNotFound("patient not found"));
        patientRepo.delete(patient);
        return new PatientResponse(mapper.mapFromPatientToResponse(patient));
    }


    // UTILITIES

    public void checkIfExists(PatientRequestDTO request){
        Optional<Patient> optionalPhone = patientRepo.findBypatientPhoneNumber(request.getPatientPhoneNumber());
        if (optionalPhone.isPresent()){
            throw new PatientConflictException("patient phone number " + request.getPatientPhoneNumber()
            + " already exists");
        }
        Optional<Patient> optionalTaxCode = patientRepo.findBytaxCode(request.getTaxCode());
        if (optionalTaxCode.isPresent()){
            throw new PatientConflictException("patient taxCode " + request.getTaxCode()
            + " already exists");
        }

    }
}





