package com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions;

public class PatientNotSavedException extends RuntimeException{

    public PatientNotSavedException() {
    }

    public PatientNotSavedException(String message) {
        super(message);
    }
}
