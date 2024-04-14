package com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions;

import org.hibernate.PropertyValueException;
import org.springframework.dao.DataIntegrityViolationException;

public class PatientNotSavedException extends PropertyValueException {

    public PatientNotSavedException(String message, String entityName, String propertyName) {
        super(message, entityName, propertyName);
    }
}
