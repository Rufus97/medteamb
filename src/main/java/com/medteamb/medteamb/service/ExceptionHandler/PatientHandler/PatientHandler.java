package com.medteamb.medteamb.service.ExceptionHandler.PatientHandler;

import com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions.PatientNotFoundException;
import com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions.PatientNotSavedException;
import com.medteamb.medteamb.service.ResponseHandler.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PatientHandler {

    @ExceptionHandler(value = PatientNotFoundException.class)
    public GenericResponse<Object> patientNotFound(PatientNotFoundException e){
        return new GenericResponse<>(HttpStatus.CONFLICT, "paziente non trovato");
    }

    @ExceptionHandler(value = PatientNotSavedException.class)
    public GenericResponse<Object> patientNotSaved(PatientNotSavedException e){
       return new GenericResponse<>(HttpStatus.BAD_REQUEST, "paziente non salvato");
    }




}
