package com.medteamb.medteamb.service.ExceptionHandler.PatientHandler;


import com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions.PatientConflictException;
import com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions.PatientNotFound;
import com.medteamb.medteamb.service.ResponseHandler.BaseResponse;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;


@RestControllerAdvice
public class PatientHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PatientNotFound.class)
    public BaseResponse patientNotFound(PatientNotFound e){
       return new BaseResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(PropertyValueException.class)
    public BaseResponse patientNotSaved(PropertyValueException e) {
        String exMessage = e.getPropertyName() + " cannot be null";
        return new BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR, exMessage);
    }


    @ExceptionHandler(PatientConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public BaseResponse patientConflictException(PatientConflictException e){
        return new BaseResponse(HttpStatus.CONFLICT, e.getMessage());
    }
  

}
