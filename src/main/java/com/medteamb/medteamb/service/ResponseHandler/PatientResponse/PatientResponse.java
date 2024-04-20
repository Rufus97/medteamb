package com.medteamb.medteamb.service.ResponseHandler.PatientResponse;


import com.medteamb.medteamb.service.ResponseHandler.BaseResponse;
import com.medteamb.medteamb.service.dto.patient.PatientDTO;


public class PatientResponse extends BaseResponse{

    PatientDTO body;


    public PatientResponse(PatientDTO body) {
        this.body = body;
    }

    public PatientDTO getBody() {
        return body;
    }

    public void setBody(PatientDTO body) {
        this.body = body;
    }
}
