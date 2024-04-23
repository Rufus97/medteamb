package com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions;

public class PatientNotFound extends RuntimeException{

    private static final long serialVersionUID = 1L;

	public PatientNotFound() {
    }

    public PatientNotFound(String message) {
        super(message);
    }

    public PatientNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}