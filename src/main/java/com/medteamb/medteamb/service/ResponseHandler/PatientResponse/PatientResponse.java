package com.medteamb.medteamb.service.ResponseHandler.PatientResponse;


import com.medteamb.medteamb.service.ResponseHandler.BaseResponse;
import com.medteamb.medteamb.service.dto.patient.PatientResponseDTO;


public class PatientResponse extends BaseResponse{

    PatientResponseDTO body;


    public PatientResponse(PatientResponseDTO body) {
        this.body = body;
    }

    public PatientResponseDTO getBody() {
        return body;
    }

    public void setBody(PatientResponseDTO body) {
        this.body = body;
    }
}
