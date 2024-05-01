package com.medteamb.medteamb.service.exception.PatientExceptions;

public class PatientNotFound extends RuntimeException{

    public PatientNotFound() {
    }

    public PatientNotFound(String message) {
        super(message);
    }

    public PatientNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}