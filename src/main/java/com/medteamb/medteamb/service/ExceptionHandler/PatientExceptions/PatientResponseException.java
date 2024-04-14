package com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions;

public class PatientResponseException extends RuntimeException{

    public PatientResponseException() {
    }

    public PatientResponseException(String message) {
        super(message);
    }

    public PatientResponseException(String message, Throwable cause) {
        super(message, cause);
    }
}