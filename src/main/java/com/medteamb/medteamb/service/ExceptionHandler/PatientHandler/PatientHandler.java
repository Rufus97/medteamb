package com.medteamb.medteamb.service.ExceptionHandler.PatientHandler;


import com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions.PatientResponseException;
import com.medteamb.medteamb.service.ResponseHandler.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class PatientHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PatientResponseException.class)
    public ResponseEntity<BaseResponse> patientNotSaved(PatientResponseException e){
        BaseResponse response = new BaseResponse(HttpStatus.NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


  

}
