package com.medteamb.medteamb.service.ResponseHandler.PatientResponse;

import com.medteamb.medteamb.service.DTO.patientDTO.PatientDTOList;
import com.medteamb.medteamb.service.ResponseHandler.BaseResponse;
import org.springframework.http.HttpStatus;

public class PatientResponseIterables extends BaseResponse {

    PatientDTOList body;


    public PatientResponseIterables(){}

    public PatientResponseIterables(PatientDTOList body){
        this.body = body;
    }

    public PatientResponseIterables(String message, PatientDTOList body) {
        super(message);
        this.body = body;
    }

    public PatientResponseIterables(HttpStatus httpStatus, String message, PatientDTOList body) {
        super(httpStatus, message);
        this.body = body;
    }

    public PatientDTOList getBody() {
        return body;
    }
    public void setBody(PatientDTOList body) {
        this.body = body;
    }
}
