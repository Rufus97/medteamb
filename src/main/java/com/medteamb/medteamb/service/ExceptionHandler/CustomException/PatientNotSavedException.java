package com.medteamb.medteamb.service.ExceptionHandler.CustomException;

import org.hibernate.PropertyValueException;

public class PatientNotSavedException extends PropertyValueException {

    public PatientNotSavedException(String message, String entityName, String propertyName) {
        super(message, entityName, propertyName);
    }
}