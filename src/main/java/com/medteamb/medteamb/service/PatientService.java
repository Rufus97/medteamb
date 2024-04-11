package com.medteamb.medteamb.service;

import com.medteamb.medteamb.model.Appointment;
import com.medteamb.medteamb.model.Patient;
import com.medteamb.medteamb.repository.AppointmentRepository;
import com.medteamb.medteamb.repository.PatientRepository;
import com.medteamb.medteamb.service.DTO.patientDTO.DTOmapper;
import com.medteamb.medteamb.service.DTO.patientDTO.PatientResponseDTO;
import com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions.PatientNotSavedException;
import com.medteamb.medteamb.service.ResponseHandler.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    AppointmentRepository appointmentRepo;
    PatientRepository patientRepo;
    DTOmapper mapper;

    public PatientService(PatientRepository patientRepo, DTOmapper mapper, AppointmentRepository appointmentRepo){
        this.patientRepo = patientRepo;
        this.mapper = mapper;
        this.appointmentRepo = appointmentRepo;
    }

    //CREATE
    public GenericResponse<PatientResponseDTO> newPatient(Patient newPatient){
       return new GenericResponse<>(mapper.mapFromPatientToResponse(patientRepo.save(newPatient)));
    }

    //READ
    // get all appointment history
    public GenericResponse<Iterable<Appointment>> getAllAppointment(Integer id){
        GenericResponse<Iterable<Appointment>> response = new GenericResponse<>();
        Iterable<Appointment> appointments = appointmentRepo.getAllPatientAppointments(id);
        if (appointments.iterator().hasNext() ){
        response.setBody(appointmentRepo.getAllPatientAppointments(id));
        response.setHttpStatus(HttpStatus.OK);
        } else {
            throw new PatientNotSavedException("appuntamenti paziente vuoti");
        }
        return response;
    }

    public GenericResponse<PatientResponseDTO> getPatient(Integer id){
        Optional<Patient> found = patientRepo.findById(id);
        GenericResponse<PatientResponseDTO> response = new GenericResponse<>();
        if (found.isPresent()){
           response.setHttpStatus(HttpStatus.OK);
           response.setBody(mapper.mapFromPatientToResponse(found.get()));
        } else {
            throw new PatientNotSavedException("paziente non trovato");
        }
        return response;
    }
    //tocca fa dto liste che palle
    public GenericResponse<Iterable<Patient>> getPatientsById(Iterable<Integer> ids){
        GenericResponse<Iterable<Patient>> response = new GenericResponse<>();
        Iterable<Patient> patients = patientRepo.findAllById(ids);
        if (patients.iterator().hasNext()){
            response.setBody(patients);
        } else {
            throw new PatientNotSavedException("lista pazienti vuota");
        }
        return response;
    }

    public GenericResponse<PatientResponseDTO> deletePatientById(Integer id){
        GenericResponse<PatientResponseDTO> response = new GenericResponse<>();
        Optional<Patient> found = patientRepo.findById(id);
        if (found.isPresent()){
            response.setBody(mapper.mapFromPatientToResponse(found.get()));
            patientRepo.delete(found.get());
        } else {
            throw new PatientNotSavedException("paziente non trovato");
        }
        return response;
    }

}





