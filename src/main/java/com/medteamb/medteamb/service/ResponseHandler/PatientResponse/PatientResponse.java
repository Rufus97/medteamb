package com.medteamb.medteamb.service.ResponseHandler.PatientResponse;

import com.medteamb.medteamb.service.DTO.patientDTO.PatientDTO;
import com.medteamb.medteamb.service.ResponseHandler.BaseResponse;
import org.springframework.http.HttpStatus;

public class PatientResponse extends BaseResponse {

    PatientDTO body;


    public PatientResponse(){}
    public PatientResponse(PatientDTO body) {
        this.body = body;
    }

    public PatientResponse(String message, PatientDTO body) {
        super(message);
        this.body = body;
    }

    public PatientResponse(HttpStatus httpStatus, String message, PatientDTO body) {
        super(httpStatus, message);
        this.body = body;
    }

    public PatientDTO getBody() {
        return body;
    }

    public void setBody(PatientDTO body) {
        this.body = body;
    }
}
