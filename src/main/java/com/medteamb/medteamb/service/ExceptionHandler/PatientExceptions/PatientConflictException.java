package com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions;

import org.hibernate.PropertyValueException;

public class PatientConflictException extends RuntimeException {


    public PatientConflictException() {
    }

    public PatientConflictException(String message) {
        super(message);
    }

    public PatientConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public PatientConflictException(Throwable cause) {
        super(cause);
    }

    public PatientConflictException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
