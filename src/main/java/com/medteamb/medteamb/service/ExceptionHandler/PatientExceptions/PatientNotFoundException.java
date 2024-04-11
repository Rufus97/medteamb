package com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions;

public class PatientNotFoundException extends RuntimeException{

    public PatientNotFoundException() {
    }

    public PatientNotFoundException(String message) {
        super(message);
    }
}