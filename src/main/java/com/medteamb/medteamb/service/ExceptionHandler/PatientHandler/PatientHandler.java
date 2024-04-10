package com.medteamb.medteamb.service.ExceptionHandler.PatientHandler;

import com.medteamb.medteamb.service.DTO.patientDTO.PatientResponseDTO;
import com.medteamb.medteamb.service.ExceptionHandler.ObjectNotFoundException;
import com.medteamb.medteamb.service.ResponseHandler.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PatientHandler {

    @ExceptionHandler(value = ObjectNotFoundException.class)
    public ResponseEntity<Object> patientNotFound(ObjectNotFoundException e){

        GenericResponse<Object> response = new GenericResponse<>();

        response.setMessage(e.getMessage());
        response.setHttpStatus(HttpStatus.NOT_FOUND);

        return  new ResponseEntity<>(response, response.getHttpStatus());
    }
}
