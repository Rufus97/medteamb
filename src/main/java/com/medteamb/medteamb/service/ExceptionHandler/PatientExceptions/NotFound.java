package com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions;

public class NotFound extends RuntimeException{

    public NotFound() {
    }

    public NotFound(String message) {
        super(message);
    }

    public NotFound(String message, Throwable cause) {
        super(message, cause);
    }
}