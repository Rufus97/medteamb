package com.medteamb.medteamb.service.ExceptionHandler.PatientHandler;

import com.medteamb.medteamb.service.DTO.patientDTO.PatientDTO;
import com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions.PatientNotFoundException;
import com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions.PatientNotSavedException;
import com.medteamb.medteamb.service.ResponseHandler.BaseResponse;
import com.medteamb.medteamb.service.ResponseHandler.PatientResponse.PatientResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PatientHandler {


    @ExceptionHandler(PatientNotSavedException.class)
    public ResponseEntity<BaseResponse> patientNotSaved(PatientNotSavedException e){
        HttpStatus thisStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>( new BaseResponse(thisStatus,
                                     e.getMessage()), thisStatus);
    }


  

}
