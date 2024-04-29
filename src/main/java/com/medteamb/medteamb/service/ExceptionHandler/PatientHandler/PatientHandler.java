package com.medteamb.medteamb.service.ExceptionHandler.PatientHandler;


import com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions.PatientNotFound;
import com.medteamb.medteamb.service.ResponseHandler.BaseResponse;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PatientHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PatientNotFound.class)
    public ResponseEntity<BaseResponse> patientNotFound(PatientNotFound e){
        BaseResponse response = new BaseResponse(HttpStatus.NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<String> patientNotSaved(PropertyValueException e) {

        String exMessage = e.getPropertyName() + " cannot be null";
       return new ResponseEntity<>(exMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  

}
